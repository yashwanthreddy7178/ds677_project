package grupo.productos.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import grupo.productos.dto.EventoEstadoPedido;
import grupo.productos.service.ProductoService;

@Component
public class ActualizadorSaldosProducto {
	
    private static final Logger log = LoggerFactory.getLogger(ActualizadorSaldosProducto.class);
    private static final String ESTADO_APROBADO = "APROBADO";
    
    @Autowired
    private ProductoService productoService;
    
    @StreamListener(CanalesProducto.ESTADO_PEDIDO_IN)
    public void compruebaEstadoPedido(EventoEstadoPedido eventoEstadoPedido) {

    	if (ESTADO_APROBADO.equals(eventoEstadoPedido.getEstadoPedido())) {
    		productoService.rebajaSaldosProductos(eventoEstadoPedido.getProductosCantidades());
    		log.info("Saldos de producto rebajados con pedido {}", eventoEstadoPedido.getPedidoId());
    	}
    }
}

package pakete.contenedor.ligavoleibolsvm;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;



public class EquiposActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Sherlock___Theme_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_equipos);
		setTitle("Equipos");
		
        FrameLayout EquiposFrameviewAndorra =(FrameLayout)findViewById(R.id.EquiposFrameviewAndorra);
        EquiposFrameviewAndorra.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewAndorra);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Andorra");
            	startActivity(OpcionesActiviyStart);
                   
            }
        }); 
        
        FrameLayout EquiposFrameviewZaragoza =(FrameLayout)findViewById(R.id.EquiposFrameviewZaragoza);
        EquiposFrameviewZaragoza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewZaragoza);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Zaragoza");
            	startActivity(OpcionesActiviyStart);
                   
            }
        }); 
        
        FrameLayout EquiposFrameviewTeruel =(FrameLayout)findViewById(R.id.EquiposFrameviewTeruel);
        EquiposFrameviewTeruel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewTeruel);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Teruel");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        FrameLayout EquiposFrameviewCajasol =(FrameLayout)findViewById(R.id.EquiposFrameviewCajasol);
        EquiposFrameviewCajasol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewCajasol);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Cajasol");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        
        FrameLayout EquiposFrameviewVigo =(FrameLayout)findViewById(R.id.EquiposFrameviewVigo);
        EquiposFrameviewVigo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewVigo);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Vigo");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        FrameLayout EquiposFrameviewNumancia =(FrameLayout)findViewById(R.id.EquiposFrameviewNumancia);
        EquiposFrameviewNumancia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewNumancia);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Soria");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        
        FrameLayout EquiposFrameviewLilla =(FrameLayout)findViewById(R.id.EquiposFrameviewLilla);
        EquiposFrameviewLilla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewLilla);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Lilla");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        FrameLayout EquiposFrameviewAlmeria =(FrameLayout)findViewById(R.id.EquiposFrameviewAlmeria);
        EquiposFrameviewAlmeria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewAlmeria);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Almeria");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        FrameLayout EquiposFrameviewIbiza =(FrameLayout)findViewById(R.id.EquiposFrameviewIbiza);
        EquiposFrameviewIbiza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewIbiza);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Ibiza");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
        
        FrameLayout EquiposFrameviewVecindario =(FrameLayout)findViewById(R.id.EquiposFrameviewVecindario);
        EquiposFrameviewVecindario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.EquipostextViewVecindario);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	            	
            	Intent OpcionesActiviyStart = new Intent(EquiposActivity.this, EquiposDetallesActivity.class);
            	OpcionesActiviyStart.putExtra("cadenaEnviar", "Vecindario");
            	startActivity(OpcionesActiviyStart);
                   
            }
        });
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnActualizar) {
        	finish();
        	startActivity(getIntent());
        } else if (item.getItemId() == R.id.btnBack) {
        	 finish();
        	startActivity(new Intent(this, Principal.class));
        }
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	 finish();
    	startActivity(new Intent(this, Principal.class));
    }
    

}

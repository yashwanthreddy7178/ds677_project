package edge.client;

import edge.dto.CheckingGetDTO;
import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient("micro-service")
public interface CreditCardClient {

    @GetMapping("/creditCard")
    @CrossOrigin
    public List<CreditCardGetDTO> findAllCreditCard();

    @GetMapping("/creditCard/by-id/{id}")
    @CrossOrigin
    public CreditCardGetDTO findById(@PathVariable Long id);

    @GetMapping("/creditCard/by-userid/{id}")
    @CrossOrigin
    public List <CreditCardGetDTO> findCreditCardByUserId(@PathVariable Long id);


    @PostMapping("/create/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid CreditCardDTO creditCardDTO);
}

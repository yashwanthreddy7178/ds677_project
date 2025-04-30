package br.com.example.jacksonrecursive.repository;

import br.com.example.jacksonrecursive.model.Versao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VersaoRepository extends JpaRepository<Versao, Long> {

    @Query("FROM Versao v JOIN FETCH v.efetividadeGrupo")
    List<Versao> findAllFetched();

}

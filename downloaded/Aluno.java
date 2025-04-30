package Cadastro;
//180905

import java.util.ArrayList;
import java.util.HashMap;


public class Aluno implements Biblioteca{ //Contem informações necessárias para aluno
    
    private String nomeAluno;
    private int idAluno;
    private int ReservaLimite;
    private int AluguelLimite;
    
    private final ArrayList<Integer> Reserva = new ArrayList();
    private final ArrayList<Integer> Alugar = new ArrayList();
    private final ArrayList<Integer> HistoricoAluguel = new ArrayList();
    
    public Aluno(int idAluno, String nomeAluno){
        this.nomeAluno = nomeAluno;
        this.idAluno = idAluno;
    }
    
    @Override
    public int getID() {
        return idAluno;
    }

    @Override
    public String getNome() {
        return nomeAluno;
    }
    
    public int getReservaLimite(){
        return ReservaLimite;
    }

    public int getAluguelLimite(){
        return AluguelLimite;
    }
    
    public ArrayList getReserva(){
        return Reserva;
    }

    public ArrayList getAlugar(){
        return Alugar;
    }

    public ArrayList getHistorico(){
        return HistoricoAluguel;
    }

    @Override
    public void setNome(String Nome) {
        this.nomeAluno = Nome;
    }

    @Override
    public void setID(int id) {
        this.idAluno = id;
    }
    
    public void setReservaLimite(int limite){
        this.ReservaLimite += limite;
    }

    public void setAluguelLimite(int limite){
        this.AluguelLimite += limite;
    }
    
    public void addReserva(int id_livro){
        this.Reserva.add(id_livro);
    }
    
    public void addAlugar(int id_livro){
        this.Alugar.add(id_livro);
    }

    public void decrementaReserva(int id_livro){
        this.Reserva.remove(new Integer(id_livro));
    }
    
    public void decrementaAlugar(int id_livro){
        this.Alugar.remove(new Integer(id_livro));
    }
    
    public void addHistorico(int id_livro){
        this.HistoricoAluguel.add(id_livro);
    }
    
    
    

    @Override
    public ArrayList getConteudo() {
        ArrayList Conteudo = new ArrayList();
        Conteudo.add(getID());
        Conteudo.add(getNome());
        Conteudo.add(this.ReservaLimite);
        Conteudo.add(this.AluguelLimite);
        return Conteudo;
    }
    
    
}

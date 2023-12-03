package bytebank.herdado;

public class Cliente implements Autenticavel {
    
    private int senha;
    private ComparaSenha autenticador;
    
    public Cliente(){
        this.autenticador = new ComparaSenha();
    }

    @Override
    public boolean autentica(int senha) {
        return this.autenticador.autentica(senha);
    }

    @Override
    public void setSenha(int senha) {
        this.autenticador.setSenha(senha);
    }


    
}

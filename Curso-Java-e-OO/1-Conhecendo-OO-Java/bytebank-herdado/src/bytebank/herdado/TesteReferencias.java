package bytebank.herdado;

public class TesteReferencias {
    
    public static void main(String[] args){
        
        // Polimorfismo exemplo, sendo gerente o mais específico e funcionário o mais genérico
        // Funcionario g1 = new Gerente();
        
        Gerente g1 = new Gerente();

        g1.setNome("Nico");
        g1.setSalario(5000.0);
        
        //Funcionario f = new Funcionario();
        //f.setSalario(2000.0);
        
        EditorVideo ev = new EditorVideo();
        ev.setSalario(2500.0);
        
        Designer d = new Designer();
        d.setSalario(2000.0);
        
        ControleBonificacao controle = new ControleBonificacao();
        controle.registra(g1);
        //controle.registra(f);
        controle.registra(ev); // Funciona para todo mundo porque todo mundo é funcionário que é a classe-mãe
        controle.registra(d);
        
        System.out.println(controle.getSoma());
    }
}

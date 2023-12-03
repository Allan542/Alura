/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bytebank.herdado;

//contrato Autenticavel
    //quem assinar esse contrato, precisa implementar
        //metodo setSenha
        //metodo autentica

// interface não pode ter nada concreto, ou seja, não pode haver implementação, sendo assim, existindo apenas métodos abstratos
// interface pode ter implementação múltipla, diferente de herança
public abstract interface Autenticavel {
    
    public abstract boolean autentica(int senha);
    
    public abstract void setSenha(int senha);
}

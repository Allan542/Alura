package br.com.alura.tdd.modelo;

import java.math.BigDecimal;

public enum Desempenho {
    A_DESEJAR {
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.03");
        }
    },
    BOM {
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.15");
        }
    },
    OTIMO{
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.2");
        }
    },
    ESPETACULAR{
        @Override
        public BigDecimal percentualReajuste() {
            return new BigDecimal("0.4");
        }
    };

    // Como existe este método abstrato dentro da classe enum, todo value de enum precisa implementar este método quando criado
    public abstract BigDecimal percentualReajuste();
}

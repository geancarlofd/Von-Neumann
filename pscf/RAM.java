//
// Arquitetura von Neumann Básica
// Prof. Luiz A. de P. Lima Jr.
// PSCF - PUCPR
//
// RAM (estende Memoria)
//

package pscf;

public class RAM extends Memoria {

    private final int [] dados;

    public int getTamanhoRam() {
        return this.tamanhoRam;
    }

    private final int tamanhoRam;

    public RAM(int W) {     // W é a capacidade da memória em "words"
        super(W);
        dados = new int[W];
        this.tamanhoRam = W;
    }

    @Override
    public int Read(int endereco) throws EnderecoInvalido {
        VerificaEndereco(endereco);
        return dados[endereco];
    }

    @Override
    public void Write(int endereco, int x) throws EnderecoInvalido {
        VerificaEndereco(endereco);
        dados[endereco] = x;

    }
}

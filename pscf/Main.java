//
// Arquitetura von Neumann Básica
// PSCF
// Prof. Luiz Lima Jr.
//
// IO <- CPU <-> RAM
//

package pscf;

public class Main {

    /*
    * Equipe:
    *
    *       Gabriel Muhlstedt Bochnia
    *       Geancarlo Ferreira Dahle
    *       Gisele Gabriel Cavalli
     */

    public static void main(String[] args) {

        System.out.println("* Arquitetura von Neumann Básica - PSCF\n");

        // cria componentes da arquitetura
        IO io = new IO(System.out);
        RAM ram = new RAM(4096);
        CacheMP cache = new CacheMP(64, ram, 64);
        CPU cpu = new CPU(io, cache);

        try {

            // carrega "programa" na memória

            final int inicio = 10;

            ram.Write(80, 118);

            cache.Read(80);

            cache.Read(80);

            cache.Write(1200, 5);
            cache.Read(1200);

            // executa programa

            //cpu.Run(1000);

        } catch (EnderecoInvalido e) {
            System.err.println("Erro: " + e);
        }
    }
}


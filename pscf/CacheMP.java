package pscf;

public class CacheMP extends Memoria {

    private int w;
    private int r;
    private int t;
    private int s;
    private final int k;
    private Linha[] cache;
    private RAM ram;

    private int capacidade;



    public CacheMP(int capacidade, RAM ram, int k) {
        super(capacidade);
        this.capacidade = capacidade;
        this.ram = ram;
        this.k = k;

        cache = new Linha[capacidade];
        for (int l = 0; l < capacidade; ++l) {
            cache[l] = new Linha(k);
        }
    }

    public void converterBinario(int endereco){
        int wBits = (int) (Math.log(this.k) / Math.log(2));
        int rBits = (int) (Math.log(cache.length) / Math.log(2));

        this.w = endereco & (int) (Math.pow(2, wBits) - 1);

        this.s = endereco >> wBits;


        this.r = s & (int) (Math.pow(2, rBits) - 1);


        this.t = (wBits + rBits);

    }

    private boolean hitMiss(int endereco) throws EnderecoInvalido {
        converterBinario(endereco);

        //System.out.println("tag: "+cache[r].tag+" - t: "+ this.t);
        if (this.t == cache[r].tag) {
            System.out.println("Cache Hit");
            return true; //Hit
        }
        System.out.println("Cache Miss");
        return false; // Miss
    }

    @Override
    int Read(int endereco) throws EnderecoInvalido {
        //Miss
        if(!hitMiss(endereco)) {
            if(cache[r].modif){
                for(int i=0; i < k; i++){
                    ram.Write(s+w, cache[r].bloco[i]);
                    System.out.println("aaaaa"+s+w);
                }
            }
            for(int i = 0; i < k; i++){
                cache[r].bloco[i] = ram.Read(s+w);
                cache[r].tag = t;
            }
            return cache[r].bloco[w];
        }
        //Hit
        return cache[r].bloco[w];
    }

    @Override
    void Write(int endereco, int valor) throws EnderecoInvalido {
        //Miss
        if(!hitMiss(endereco)) {
            for(int i = 0; i < k; i++){
                cache[r].bloco[i] = ram.Read(s+w);
            }
            cache[r].tag = t;
        }
        //Hit
        cache[r].bloco[w] = valor;
        cache[r].modif = true;
    }
}

class Linha {
    public int tag;
    public boolean modif;
    public int[] bloco;

    public Linha(int k) {
        this.bloco = new int[k];
       // this.tag = -1;
    }
}
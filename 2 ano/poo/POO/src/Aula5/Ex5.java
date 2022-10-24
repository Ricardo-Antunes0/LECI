package Aula5;
import java.util.*;

public class Ex5 {
    public static Livro[] livros = new Livro[20];
    public static Utilizador[] alunos = new Utilizador[20];

    static  Scanner sc = new Scanner(System.in); 
    public static void main(String[] args) {
        int aux;
        do{
            print();
            aux = sc.nextInt();

            switch(aux){
                case 1:
                    inscricao();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    printAllStudents();
                    break;
                case 4:
                    registerBook();
                    break;
                case 5:
                    printAllBooks();
                    break;
                case 6:
                    emprestar();
                    break;
                case 7:
                    devolver();
                    break;
                }
        }while(aux != 8);
    } 
   
    public static void print(){
        System.out.println("1 - inscrever utilizador");
        System.out.println("2 - remover utilizador");
        System.out.println("3 - imprimir lista de utilizadores");
        System.out.println("4 - registar um novo livro");
        System.out.println("5 - imprimir lista de livros");
        System.out.println("6 - emprestar");
        System.out.println("7 - devolver");
        System.out.println("8 - sair");
    }

    public static void inscricao(){
        System.out.println("Nome: ");
        String nome = sc.next();
        System.out.println("nMec: ");
        int nMec = sc.nextInt();
        System.out.println("Curso: ");
        String curso = sc.next();

        for(int i = 0; i < alunos.length; i++){
            if(alunos[i] == null)
                alunos[i] = new Utilizador(nome,nMec,curso);    
            else continue;
        }
    }

    public static void remover(){
       System.out.print("Nmec do user que pretende remover: ");
       int nMec = sc.nextInt();

        for(int i = 0; i < alunos.length; i++){
            if(alunos[i].nmec() == nMec){
                alunos[i] = null;
            }else{
                continue;
            }
        }
    } 

    public static void printAllStudents(){
        for(int i = 0; i < alunos.length; i++){
            if(alunos[i] == null){
                continue;
            }
            System.out.println(alunos[i]);
        }
    }

    public static void registerBook(){
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Tipo de emprestimo: ");
        String tipoEmprestimo = sc.nextLine();

        for(int i = 0; i < livros.length; i++){
            if(livros[i] == null)   livros[i] = new Livro(titulo,tipoEmprestimo);
            else   continue;
        }  
    }

    public static void printAllBooks() {
        for (Livro u : livros) {
            if (u == null) {
                continue;
            }
            System.out.println(u);
        }
    }

    public static void emprestar() {
        printAllStudents();
        System.out.print("nMec do aluno que quer fazer o emprestimo: ");
        int nMec = sc.nextInt();
        printAllBooks();
        System.out.print("ID do livro a ser emprestado: ");
        int id = sc.nextInt();
        for (Livro l : livros){
            if (l == null)  continue;
            if (l.getId() == id){
                for (Utilizador aluno : alunos){
                    if (aluno == null)  continue;
                    if (aluno.nmec() == nMec) {
                        if (aluno.getLivrosRequisitados().size() < 3 && !l.getTipoEmprestimo().equals("CONDICIONADO") &&  l.disponivel()) {
                            l.setDisponivel(false);
                            ArrayList<Integer> temp = aluno.getLivrosRequisitados();
                            temp.add(id);
                            aluno.setLivrosRequisitados(temp);
                        }
        
                        break;
                    }
                }
            }
        }
    }

    public static void devolver() {
        printAllStudents();
        System.out.print("nMec do Aluno que quer fazer a devolução: ");
        int nMec = sc.nextInt();
        printAllBooks();
        System.out.print("ID do livro a ser devolvido: ");
        int id = sc.nextInt();
        for (Livro l : livros) {
            if (l == null) {
                continue;
            }
            if (l.getId() == id) {
                for (Utilizador aluno : alunos) {
                    if (aluno == null)  continue;

                    if (aluno.nmec() == nMec) {
                        if (aluno.getLivrosRequisitados().contains(l.getId())) {
                            l.setDisponivel(true);
                            ArrayList<Integer> temp = aluno.getLivrosRequisitados();
                            for (int i = 0; i < temp.size(); i++) {
                                if (temp.get(i) == id)  temp.remove(i);
                            }
                            aluno.setLivrosRequisitados(temp);
                        }
                        break;
                    }
                }
            }
        }
    }
}

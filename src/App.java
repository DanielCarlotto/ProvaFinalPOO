
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class App {
            static String opcao = "0";
            static Scanner scanner = new Scanner(System.in);
            static List<Produto> lista_produtos = new ArrayList<>();
            static int qtd_produtos = 0;
            static int qtd_produtos_venda = 0;
            
            

   

    public static void main(String[] args) throws Exception {
        


        do {

            System.out.println("**********MENU PRINCIPAL**********");
            System.out.println("1. INCLUIR PRODUTO");
            System.out.println("2. CONSULTAR PRODUTO");
            System.out.println("3. LISTAR PRODUTOS");
            System.out.println("4. VENDAS POR PERIODO - DETALHADO");
            System.out.println("5. REALIZAR VENDA");
            System.out.println("0. SAIR");
            System.out.print("OPCÃO: ");
            opcao = scanner.nextLine();

            
            switch (opcao) {
                case "1":{
                    incluirProduto();
                    break;
                }
                case "2":{
                    consultar_produto_por_codigo();
                    break;
                }
                case "3":{
                    listarProdutos();
                    break;
                } 
                case "4": {
                    vendasPorPeriodo();
                    break;
                }
                case "5":{
                    realizarVenda();
                    break;
                }
                case "0":{
                    System.out.println("Saindo do sistema!");
                    break;
                }
            default:
                System.out.println("Opção invalida!!!");
                break;
            }
        } while (!opcao.equals("0"));

            

    }

    private static void incluirProduto() {
    
    
    Produto produto = new Produto();

    System.out.println("*****Cadastro de produtos*****\n");
    produto.setdata(LocalDate.now());
    System.out.println("Informe o nome do produto: ");
    produto.setNome(scanner.nextLine());
    System.out.println("Informe o código do produto: ");
    produto.setCodigo(scanner.nextLine());
    System.out.println("Informe o valor do produto: ");
    produto.setValor( scanner.nextDouble());
    System.out.println("Informe a quantidade que deseja cadastrar: ");
    produto.setqtdEstoque(Integer.parseInt(scanner.next()));
          
    
    lista_produtos.add(produto);

    
    
    
    System.out.println("Produto cadastrado com sucesso!!! \n");
    System.out.println("Precione ENTER para voltar ao MENU....");
    scanner.nextLine();
    }

    

    static boolean controle = false;
    static boolean consultar_produto_por_codigo() {
        System.out.println("*****Consulta de produtos*****");
       System.out.println("Informe o código do produto que deseja consultar: ");
       String pesquisa = scanner.nextLine();
       for (Produto produto : lista_produtos) {
           if (produto != null && produto.getcodigo().equals(pesquisa)) {
               System.out.println("Produto encontrado: \n");
               System.out.println(produto);
               return true;
           
       } else {
           System.out.println("Produto não cadastrado ou código incorreto!!!");
       }
       }
    return false;
            
    }

    private static void listarProdutos(){
        
        if (lista_produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados para exibir!!! \n");
        } else {
            System.out.println("*****Lista de produtos cadastrados*****");
        for (Produto item : lista_produtos) {
            System.out.println(item);
               
        }
            DoubleSummaryStatistics resumo = lista_produtos.stream()
            .collect(Collectors.summarizingDouble(Produto::getvalor));
            System.out.println("***Resumo***");
            System.out.printf(" Media: %.2f, Maior valor: %.2f, Menor valor: %.2f\n", resumo.getAverage(), resumo.getMax(), resumo.getMin());
            System.out.println("Precione ENTER para voltar ao MENU....");
            scanner.nextLine();
   
        } 

    }
    
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static int novo_qtd_estoque = 0;
    static Double novoValorVenda = 0.0;
    static Double valorTotalVenda = 0.0;
    static boolean achei = false;
    // minha venda esta bugado, nao consegui descobrir o erro. ela faz a venda mas de forma bugada
    private static  boolean  realizarVenda() {
        System.out.println("*****Realizar Venda*****");
        System.out.println("informe a data da venda(dd/mm/yyyy) ou pressione ENTER para pegar a data de hoje: ");
        String data = scanner.nextLine();
        for (Produto produto : lista_produtos) {
        if (data.equals("")) {
            System.out.println( LocalDate.now().format(dtf));
            produto.setdata(LocalDate.now());
        } else {
            LocalDate data1 = LocalDate.parse(data, dtf); 
            System.out.println( data1.format(dtf) );
            produto.setdata(data1);
        }
    }
        System.out.println("Informe o codigo do produto a ser vendido: ");
        String codigoVendido = scanner.next();
        for (Produto item : lista_produtos) {
        if(item != null && item.getcodigo().equals(codigoVendido)) {
           achei = true;
           System.out.println("produto encontrado: [" + codigoVendido + "]");
           
           
        } else{
           System.out.println("Produto não localizado!!!");           
        }

        System.out.println("Informe a quantia que deseja vender: ");
        int qtd_produtos_venda = Integer.parseInt(scanner.next());
        if(item.getcodigo().equals(codigoVendido) && item.getqtdEstoque() >= qtd_produtos_venda  ) {
           novo_qtd_estoque = (item.getqtdEstoque() - qtd_produtos_venda);
           item.setqtdEstoque(novo_qtd_estoque);
           novoValorVenda = item.getvalor()*qtd_produtos_venda;
           valorTotalVenda = valorTotalVenda + novoValorVenda;
           item.setValorToalVenda(valorTotalVenda);
           

        System.out.println("Venda realziada com sucesso.");
        System.out.println(item);
        scanner.nextLine();        
        } else {
        System.out.println("Quantidade indisponivel!!!");
        scanner.nextLine();
            
        }
        }
        return controle;
           
            
    }
        
        static void vendasPorPeriodo() {
            
            System.out.println("*****Relatorio de vendas por periodo*****");
            System.out.println("informe a data do periodo(dd/mm/yyyy) ou pressione ENTER para pegar a data de hoje: ");
        String data = scanner.nextLine();
        if (data.equals("")) {
            System.out.println( LocalDate.now().format(dtf) );
        } else {
            LocalDate data1LocalDate = LocalDate.parse(data, dtf); 
            System.out.println( data1LocalDate.format(dtf) );
        }
            Map<LocalDate, List<Produto>> produtos_agrupados_data = lista_produtos.stream()
            .collect(Collectors.groupingBy(Produto::getdata));

            produtos_agrupados_data.entrySet().forEach(
                produto -> System.out.println("Data : " + produto.getKey().format(dtf) + " " + produto.getValue())
                
            );

            Map<LocalDate, Double> produtos_agrupados_data_media = lista_produtos.stream()
            .collect(Collectors.groupingBy(Produto::getdata , Collectors.averagingDouble(Produto::getValorTotalVenda)));

            produtos_agrupados_data_media.entrySet().forEach(
                produto -> System.out.println("Valor medio das vendas: " + produto.getValue())
            );
            



        }
















}

       
    
    

    
    
            
    

        

    

    


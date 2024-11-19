import java.util.*

fun imprimeMenu() {
    println()
    println("Bem-vindo ao jogo \"4 Baloes em Linha\"!")
    println()
    println("1. Novo Jogo")
    println("2. Gravar Jogo")
    println("3. Ler Jogo")
    println("0. Sair")
    println()
}

fun validaOpcao(): Int {
    do {
        val opcao = readln().toIntOrNull()
        if (opcao != null && opcao in 0..3) {
            return opcao
        }
        else {
            println("Opcao invalida. Por favor, tente novamente.")
        }
    } while (true)
}

fun pedeLinhasOuColunas(linhaOuColuna:String):Int {
    do {
        println("Numero de $linhaOuColuna:")
        val quantidade = readln().toIntOrNull() ?: 0
        if (quantidade > 0) {
            return quantidade
        }
        else println("Numero invalido")
    } while (true)
}

fun pedeColunaparaJogar(colunas:Int): String {
    do {
        val intervaloColunas = (Char(65)..Char(64+colunas))
        println("Coluna? ($intervaloColunas):")
        val colunaEscolhida = readln()
        if (processaColuna(colunas, colunaEscolhida)) {
            println("Coluna escolhida: ${colunaEscolhida.uppercase(Locale.getDefault())}")
            return colunaEscolhida
        }
        else {
            println("Coluna invalida")
        }
    } while (true)
}

fun validaTabuleiro(numLinhas:Int?, numColunas:Int?): Boolean {
    if (numLinhas == null || numColunas == null) {
        return false
    }
    if (numColunas == numLinhas + 1) {
        when (numLinhas) {
            5,6,7 -> return true
        }
    }
    return false
}

fun processaColuna(numColunas:Int, coluna:String): Boolean {
    if (numColunas in (1..26) && coluna.length == 1 && coluna[0].uppercaseChar() in Char(65)..Char(90)) {
        return coluna[0].uppercaseChar().code - 64 <= numColunas
    }
    return false
}

fun nomeValido(nome:String): Boolean {
    var count = 0
    while (count < nome.length) {
        if (nome[count] == ' ') {
            return false
        }
        count++
    }
    return nome.length in (3..12)
}

fun criaTopoTabuleiro(numColunas:Int): String {
    var topoTabuleiro = "\u2554"
    while (topoTabuleiro.length < numColunas*4) {
        topoTabuleiro += "\u2550"
    }
    topoTabuleiro += "\u2557"
    return topoTabuleiro
}

fun criaLegendaHorizontal(numColunas:Int): String {
    var legendaHorizontal = " "
    var count = 0
    while (legendaHorizontal.length < criaTopoTabuleiro(numColunas-1).length) {
        legendaHorizontal += " ${Char(65 + count)} |"
        count++
    }
    legendaHorizontal += " ${Char(65 + count)}  "
    return legendaHorizontal
}

fun criaLinhasTabuleiro(numLinhas: Int,numColunas: Int): String {
    var linhaTabuleiro = "\u2551"
    var count = 0
    while (linhaTabuleiro.length < criaTopoTabuleiro(numColunas-1).length) {    //Imprimir linhas com baloao?
        linhaTabuleiro += "   |"
        count++
    }
    linhaTabuleiro += "   \u2551"

    var meioTabuleiro = ""
    count = 0
    while (count < numLinhas-1) {
        meioTabuleiro += linhaTabuleiro + "\n"
        count++
    }
    meioTabuleiro += linhaTabuleiro
    return meioTabuleiro
}

fun criaTabuleiro(numLinhas: Int,numColunas: Int,mostraLegenda:Boolean = true): String {
    var tabuleiro = criaTopoTabuleiro(numColunas) + "\n" + criaLinhasTabuleiro(numLinhas,numColunas)
    if (mostraLegenda) {
        tabuleiro += "\n" + criaLegendaHorizontal(numColunas)
    }
    return tabuleiro
}

fun mostraPorImplementar() {
    println("POR IMPLEMENTAR")
}

fun main() {
    val mostrarLegenda = true                                         //Ativa/Desativa legenda do tabuleiro
    imprimeMenu()                                                     //Imprime o Menu (Opcoes)
    do {
        var opcao:Int? = validaOpcao()                                //Valida a opcao selecionada
        if (opcao == 1) {
            var linhas: Int
            var colunas: Int
            do {
                linhas = pedeLinhasOuColunas("linhas")   //Pede e valida o numero linhas
                colunas = pedeLinhasOuColunas("colunas") //Pede e valida o numero de colunas
                if (!validaTabuleiro(linhas, colunas)) {             //Verifica se combinacao e valida
                    println("Tamanho do tabuleiro invalido")
                }
            } while (!validaTabuleiro(linhas, colunas))              //Sai do ciclo quando o utilizador escolher uma combinação valida
            var nome: String
            do {                                                      //Pede e valida o nome
                println("Nome do jogador 1:")
                nome = readln()
                if (!nomeValido(nome)) {
                    println("Nome de jogador invalido")
                }
            } while (!nomeValido(nome))
            println(criaTabuleiro(linhas, colunas, mostrarLegenda))  //Imprime o tabuleiro
            println("\n"+nome)                                       //Imprime o nome do Jogador (Afastado uma linha do tabuleiro)
            println("Tabuleiro ${linhas}X${colunas}")                //Imprime o tamanho do tabuleiro
            var jogada = pedeColunaparaJogar(colunas)
            mostraPorImplementar()                                    //Imprime "POR IMPLEMENTAR"
            opcao = null                                              //Termina programa (opcao == null termina o ciclo)
        }
        if (opcao == 2) {
            mostraPorImplementar()
        }
        if (opcao == 3) {
            mostraPorImplementar()
        }
        if (opcao == 0) {
            println("A sair...")
        }
    } while (opcao != 0 && opcao != null)
}


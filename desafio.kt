class IllegalRegistrationException(message: String): Throwable(message)

enum class Nivel { BASICO, INTERMEDIARIO, AVANÇADO }

data class Usuario(val nome: String)

data class ConteudoEducacional(val nome: String, val duracaoMin: Int, val nivel: Nivel) 

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val niveis: List<Nivel>) {
	
    fun verificarNivel(): Nivel {
        val niveisConteudos = conteudos.map { it.nivel } //  lista de níveis correspondentes aos conteúdos educacionais da formação.
    	val nivelMaisAlto = niveisConteudos.maxOrNull() // encontra o nível mais alto na lista de níveis dos conteúdos educacionais para esta formação.
        return nivelMaisAlto ?: Nivel.BASICO // retorna o nivel mais alto, mas se for null (ou seja, se a lista estiver vazia), retornará Nivel.BASICO como um valor padrão. 
    }
    
    fun verificaDuracaoTotal(): Int {
        val duracao = conteudos.map { it.duracaoMin }
        return duracao.sum()
    }
    val inscritos = mutableListOf<Usuario>()
    
    @Throws(IllegalArgumentException::class)
    fun matricular(vararg usuarios: Usuario) {
    try {
        if (usuarios.isEmpty()) {
            throw IllegalRegistrationException("Nenhum usuário especificado para matrícula.")
        }
        inscritos.addAll(usuarios)
    } catch (e: IllegalRegistrationException) {
        println("Erro ao matricular usuários: ${e.message}")
    }
}

}

fun main() {
    
    val usuario1 = Usuario("Caio")
    val usuario2 = Usuario("Elidiane")
    val usuario3 = Usuario("Venilton")
    
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 60, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos em Kotlin", 100, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Tratamento de Exceções em Kotlin", 120, Nivel.INTERMEDIARIO)
    
    val formacaoKotlin = Formacao("Kotlin", listOf(conteudo1, conteudo2, conteudo3), listOf(Nivel.BASICO, Nivel.INTERMEDIARIO))
    val KotlinTest = Formacao("KotlinTeste", listOf(conteudo1, conteudo2, conteudo3), listOf(Nivel.BASICO, Nivel.INTERMEDIARIO))
    
    formacaoKotlin.matricular(usuario1, usuario2, usuario3)
    KotlinTest.matricular() // Testando o tratamento de exceções
    
    println("Formação: ${formacaoKotlin.nome}")
    println("--------------------------------")
    println("Conteúdos:")
    for (conteudo in formacaoKotlin.conteudos) {
        println("- ${conteudo.nome} (Nível: ${conteudo.nivel})")
    }
    println("--------------------------------")
    println("Nível da Formação: ${formacaoKotlin.verificarNivel()}")
    println("Duração da Formação: ${formacaoKotlin.verificaDuracaoTotal()}min")
    println("--------------------------------")
    println("Inscritos na Formação:")
    for (inscrito in formacaoKotlin.inscritos) {
        println("- ${inscrito.nome}")

	}

}
/* 
No construtor da classe
	OK Receba por par�metro um valor inteiro referente �s dimens�es do tabuleiro
	OK Com este valor recebido, inicialize o tabuleiro
	OK Crie o m�todo realizaJogada
		OK que recebe dois inteiros (referentes a uma linha e uma coluna do tabuleiro) 
		OK e um caractere, referente a jogada a ser realizada ('X' ou 'O'). 
		OK Este m�todo retorna verdadeiro ou falso, 
			OK e simplesmente coloca no tabuleiro a jogada realizada. 
			OK Caso j� tenha uma jogada na posi��o indicada, o m�todo retorna falso, indicando que a jogada n�o pode ser realizada. 
			OK Caso a jogada seja realizada corretamente, o m�todo retorna verdadeiro.

OK Crie um m�todo chamado verificaGanhador
	OK que retorna um valor booleano
	OK Este m�todo analisa o tabuleiro e retorna verdadeiro caso o jogo tenha um vencedor, e falso caso contr�rio. 
	OK O jogo tem um vencedor caso as duas diagonais (principal e secund�ria) estejam preenchidas com o mesmo caractere ('X' ou 'O'), 
	OK ou quando alguma linha estiver preenchida com o mesmo caractere ('X' ou 'O'), 
	OK ou ainda quando alguma coluna estiver preenchida com o mesmo caractere ('X' ou 'O').
	
Na classe JogoDaVelha
	OK fa�a o m�todo toString, que retorna uma String que refere-se ao estado atual do tabuleiro. 
*/
import javax.management.RuntimeErrorException;

public class JogoDaVelha {
    private String[][] _tabuleiro;
    
    public JogoDaVelha(int dimensao){
        _tabuleiro = new String[dimensao][dimensao];
    }
    
    public boolean realizaJogada(int linha, int coluna, String jogada) throws RuntimeException{
        if(linha < 1 || linha > _tabuleiro.length){
        	throw new RuntimeErrorException(null, "Linha n�o existe.");
        }
        else if(coluna < 1 || coluna > _tabuleiro[linha-1].length) {
        	throw new RuntimeErrorException(null, "Coluna n�o existe.");
        }
        
        if(jogada == null || jogada.trim().isEmpty()) {
        	throw new RuntimeErrorException(null, "Especifique a jogada.");
        }
        else if(!(jogada.toUpperCase().equals("X") || jogada.toUpperCase().equals("O"))) {
        	throw new RuntimeErrorException(null, "Informe X ou O para a jogada.");
        }
        
        String jogadaAnterior = _tabuleiro[linha-1][coluna-1];
        
        if(jogadaAnterior == null || jogadaAnterior.trim().isEmpty()) {
        	_tabuleiro[linha-1][coluna-1] = jogada;
        	
        	return true;
        }
        
        return false;
    }
    
    public boolean verificaGanhador() {
    	
    	if(verificaLinhas() || verificaColunas() || verificaDiagonal() || verificaDiagonalInversa()) {
    		return true;
    	}
    	
    	return false;
    }
    
    private boolean verificaLinhas() {
    	for(int i=0; i < _tabuleiro.length; i++) {
    		String jogadaMestre = new String();
    		int jogadasIguais = 0;
    		
    		for(int j=0; j < _tabuleiro[i].length; j++) {
    			String jogadaDaCasa = _tabuleiro[i][j];
    			
    			if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
    				break;
    			}
    			else {
    				if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
    					jogadaMestre = jogadaDaCasa;
        			}
    				
    				if(!jogadaDaCasa.equals(jogadaMestre)){
        				break;
        			}
    				else {
    					jogadasIguais++;
    				}
    			}
    		}
    		
    		if(jogadasIguais == _tabuleiro[i].length) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean verificaColunas() {
    	for(int i=0; i < _tabuleiro.length; i++) {
    		String jogadaMestre = new String();
    		int jogadasIguais = 0;
    		
    		for(int j=0; j < _tabuleiro[i].length; j++) {
    			String jogadaDaCasa = _tabuleiro[j][i];
    			
    			if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
    				break;
    			}
    			else {
    				if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
    					jogadaMestre = jogadaDaCasa;
        			}
    				
    				if(!jogadaDaCasa.equals(jogadaMestre)){
        				break;
        			}
    				else {
    					jogadasIguais++;
    				}
    			}
    		}
    		
    		if(jogadasIguais == _tabuleiro.length) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean verificaDiagonal() {
    	String jogadaMestre = new String();
		int jogadasIguais = 0;
		
    	for(int i=0; i < _tabuleiro.length; i++) {
    		String jogadaDaCasa = _tabuleiro[i][i];
			
			if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
				break;
			}
			else {
				if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
					jogadaMestre = jogadaDaCasa;
    			}
				
				if(!jogadaDaCasa.equals(jogadaMestre)){
    				break;
    			}
				else {
					jogadasIguais++;
				}
			}
    	}
    	
    	if(jogadasIguais == _tabuleiro.length) {
			return true;
		}
    	
    	return false;
    }
    
    private boolean verificaDiagonalInversa() {
    	String jogadaMestre = new String();
		int jogadasIguais = 0;
		
    	for(int i=0; i < _tabuleiro.length; i++) {
    		String jogadaDaCasa = _tabuleiro[i][_tabuleiro.length - 1 - i];
			
			if(jogadaDaCasa == null || jogadaDaCasa.trim().isEmpty()){
				break;
			}
			else {
				if(jogadaMestre == null || jogadaMestre.trim().isEmpty()){
					jogadaMestre = jogadaDaCasa;
    			}
				
				if(!jogadaDaCasa.equals(jogadaMestre)){
    				break;
    			}
				else {
					jogadasIguais++;
				}
			}
    	}
    	
    	if(jogadasIguais == _tabuleiro.length) {
			return true;
		}
    	
    	return false;
    }
    
    public void desenhaJogo() {
    	System.out.println("====================");
    	
    	for(int i=0; i < _tabuleiro.length; i++) {
    		for(int j=0; j < _tabuleiro[i].length; j++) {
    			String jogadaAnterior = _tabuleiro[i][j];
    			
    			System.out.print(((jogadaAnterior != null && !jogadaAnterior.trim().isEmpty()) ? jogadaAnterior : "-")  + "\t");
    		}
    		
    		System.out.println("");
    	}
    	
    	System.out.println("====================");
    }
    
    public String toString() {
    	return verificaGanhador() ? "Finalizado" : "Em andamento";
    }
}

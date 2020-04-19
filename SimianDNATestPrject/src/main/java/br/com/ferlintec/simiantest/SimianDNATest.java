package br.com.ferlintec.simiantest;

import br.com.ferlintec.exception.InvalidDNAException;

public class SimianDNATest {

	private static final String SEQUENCIA_REPETIDA_REGEX = ".*A{4}.*|.*T{4}.*|.*C{4}.*|.*G{4}.*";
	private static final String LETRA_VALIDA_DNA_REGEX = "[ATCG]+";
	
	public boolean isSimian(String[] dna) {
		
		if (dna != null) {
			
			if (!isMatrizQuadrada(dna))
				throw new InvalidDNAException("Não é uma matriz quadrada.");
		
			if (!isBaseDNAValida(dna))
				throw new InvalidDNAException("O DNA contém letras inválidas, diferentes de 'ATCG'.");
			
			if (verificarSequenciaRepetidaLinhas(dna))
				return true;
			
			char[][] matrizDNA = montarMatriz(dna);
			if (verificarSequenciaRepetidaLinhas(dna))
				return true;
			else
				if (verificarSequenciaRepetidaColunas(matrizDNA))
					return true;
				else
					if (verificarSequenciaRepetidaVerticais(matrizDNA))
						return true;
		}
		
		return false;
	}
	
	protected boolean verificarSequenciaRepetidaLinhas(String[] dna) {
		
		for (String sequencia : dna) {
			if (isQuatroRepetidas(sequencia))
				return true;
		}
		return false;
	}
	
	
	protected boolean verificarSequenciaRepetidaColunas(char[][] matrizDNA) {
		 
		int dimensao = matrizDNA.length;
		
		StringBuilder sequencia = new StringBuilder();
		
		for (int coluna = 0; coluna < dimensao; coluna++) {
			
			for (int linha = 0; linha < dimensao; linha++) {
				sequencia.append(matrizDNA[linha][coluna]);
			}
			
			if (isQuatroRepetidas(sequencia.toString())) {
				return true;
			}
			
			sequencia.delete(0, dimensao);
		}
		return false;
	}
	
	
	/**
	 * Verifica se há sequência repetida, com 4 letras, em alguma diagonal.
	 * 
	 * @param matrizDNA
	 * @return TRUE se encontrar sequência repetida nas diagonais, FALSE, caso contrário.
	 */
	protected boolean verificarSequenciaRepetidaVerticais(char[][] matrizDNA) {
		 
		int dimensao = matrizDNA.length;
		
		StringBuilder sequencia = new StringBuilder();
		for (int linha = 3; linha < dimensao; linha++) {
			
			for (int coluna = 0; coluna < linha+1; coluna++) {
				sequencia.append(matrizDNA[linha-coluna][coluna]);
			}
			
			if (isQuatroRepetidas(sequencia.toString())) {
				return true;
			}
			
			sequencia.delete(0, sequencia.toString().length());
		}
		
		for (int coluna  = 3; coluna < dimensao; coluna++) {
			int count = 0;
			for (int linha = dimensao-1; coluna-count >= 0; linha--,count++) {
				sequencia.append(matrizDNA[linha][coluna-count]);
			}
			
			if (isQuatroRepetidas(sequencia.toString())) {
				return true;
			}
			
			sequencia.delete(0, sequencia.toString().length());
		}
		
		for (int linha = 1; linha+3 < dimensao; linha++) {
			int count = 0;
			for (int coluna = dimensao-1; coluna-linha >=0; coluna--, count++) {
				sequencia.append(matrizDNA[linha+count][coluna]);
			}
			
			if (isQuatroRepetidas(sequencia.toString())) {
				return true;
			}
			
			sequencia.delete(0, sequencia.toString().length());
		}
		
		for (int coluna  = dimensao-4; coluna > 0; coluna--) {
			for (int linha = 0; coluna+linha < dimensao; linha++) {
				sequencia.append(matrizDNA[linha][coluna+linha]);
			}
			
			if (isQuatroRepetidas(sequencia.toString())) {
				return true;
			}
			
			sequencia.delete(0, sequencia.toString().length());
		}
		
		return false;
	}
	
	protected char[][] montarMatriz(String[] dna){
		int dimensao = dna.length;
		char[][] matriz = new char[dimensao][dimensao];
		
		for (int linha = 0; linha < dimensao; linha++) {
			String sequencia = dna[linha];
			
			for (int coluna = 0; coluna < dimensao; coluna++) {
				matriz[linha][coluna] = sequencia.charAt(coluna);
			}
		}
		
		return matriz;
	}
	
	/**
	 * Verifica se as letras da sequência nitrogenada do DNA são válidas.
	 * 
	 * @param dna
	 * @return TRUE, caso o DNA contenha apenas as letras A,T, C e G, 
	 * 			ou FALSE, caso contrário.
	 */
	protected boolean isBaseDNAValida(String[] dna) {
		for (String sequencia : dna) {
			if (!sequencia.toUpperCase().trim().matches(LETRA_VALIDA_DNA_REGEX))
				return false;
		}
		return true;
	}
	
	
	/**
	 * Verifica se há uma sequência repetida de 4 letras válidas no DNA.
	 * @param sequencia
	 * @return TRUE se houver pelo menos 4 letras repetidas em sequência,
	 * 			FALSE, caso contrário.
	 */
	protected boolean isQuatroRepetidas(String sequencia) {
		if (sequencia != null)
			return sequencia.trim().toUpperCase().matches(SEQUENCIA_REPETIDA_REGEX);
		
		return false;
	}


	/**
	 * Verificar se a matriz de DNA é quadrada.
	 * 
	 * @param dna
	 * @return TRUE se for  quadrada, e FALSE, caso contrário.
	 */
	protected boolean isMatrizQuadrada(String[] dna) {
		int dimensao = dna.length;
	
		for (String sequencia : dna) {
			if (sequencia.trim().length() != dimensao)
				return false;
		}
		return true;
	}
	
}

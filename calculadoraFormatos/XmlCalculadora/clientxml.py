import operator
import xml.etree.cElementTree as ET
import socket		 	 
import sys
import ipaddress

if __name__ == '__main__':
    s = socket.socket()		#criando socket
    host = '192.168.1.8'     #fornecendo endereço IP	
    port = 3000                           # forencendo número da porta
    s.connect((host, port))                           # Conectando com o servidor
    print("Endereço IP do servidor:", host)
    print("Porta do servidor:", port)
    stack = []					#inciando lista vazia
    while True:					#laço para manter a calculadora ativa até que o usuário resolva sair
        expression = input('Escreva o cálculo em notação polonesa reversa (Ex: 5 3 8 / -). Use "q" para sair > ')	#input para receber a expressão notação polonesa reversa
        if len(expression)==0:			#condição para caso nada ser escrito o input se manter
            continue
        stack = expression.split()		#divide cada elemento da expressão em um item da lista vazia criada anteriormente
        data = ET.Element('data')		#criando um elemento raiz xml com o tag 'data' 
        items = ET.SubElement(data, 'items')	#criando um subelemento xml de 'data 'com a tag 'items'
        for i in stack:				#laço para criar um subelemento para cada item da expressão que foi armazenado na lista 'stack' antriormente
            item = ET.SubElement(items, 'item')	#cria um subelemento xml para 'items' com a tag 'item'
            item.set('name',(i))		#adiciona o atributo ao subelemento
            item.text = i			#cria o texto do elemento que no caso será um dos elementos da lista 'stack'
        mydata = ET.tostring(data, encoding='utf8', method='xml')	#transforma o formato xml criado em uma string sendo codificada no formato utf8
        s.send(mydata)				#envia os dados para o servidor
        result = s.recv(1024).decode()		#espera receber a resposta vinda do servidor e armazena o resultado
        if result == "Quit":			#condição para caso o cliente escolheu terminar de realizar cálculos
            print("Conexão Fechada")
            break
        print("Resultado:", result)		#imprime o resultado
    s.close				#fecha a conexão

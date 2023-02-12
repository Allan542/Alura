# Regex Significados

 - "\d" ou all digits: Todos os números;
 - "\D" ou non-digit: Tudo que não seja número ou digits. É um atalho para [^\d];
  
 - ".": Qualquer caractere;
  
 - "\": Faz alguns caracteres especiais do regex(meta-chars) serem entendidos do jeito literal, ou seja, "\." irá buscar todos os pontos no texto. Algumas linguagens precisam que imprima até caracteres especiais não relacionados a regex (não meta-chars);
  
 - "{nº de vezes} ou quantifier": Um conjunto de caracteres que define quantas vezes exatas um caractere deveria aparecer (ex: {3}, deve aparecer 3 exatas vezes). Também, pode ser definido um tamanho mínimo e um tamanho máximo (ex: {1,3}, entre 1 e 3 vezes) e somente um tamanho mínimo (ex: {1,}, uma ou mais vezes);

 - "*": Serve para definir uma quantidade de caracteres que devem aparecer zero ou mais vezes;

 - "?": Significa que eu quero que um determinado caractere na regex seja opcional, ou seja, apareça zero ou uma vez;
  
 - "[chars] ou classe de caracteres": Um conjunto de caractere que pode aparecer em uma determinada posição. Não precisa de \ para indicar o valor literal. Também pode definir de um conjunto de números ou letras, começando de um e terminando em outro(ex: [0-9] que também significa o "\d" ou [a-z], só minúscula ou [A-Z], só maiúscula). Para definir este tipo de classe, é necessário o hífen(-) como separador para identificar que queremos tal coisa até tal coisa. Neste exemplo [1-36-9], ele está dizendo que quer números entre 1 e 3 E/OU números entre 6 e 9. O Mesmo vale para letras [A-Za-z] (De a até z Ou mínuscula ou maiúscula), [abc] (a, b ou c):
   - Nem todos os meta-chars funciona dentro de uma classe, mas a \ para buscar um literal ou um número por exemplo (\d), funciona. Os outros metachars que funcionam são: o hífen (-), que significa entre um e outro e o acento circunflexo (^), que significa negação e não é uma âncora por estar dentro apenas de classes.
  
 - "+": Serve para definir uma quantidade de caracteres que devem aparecer uma ou mais vezes;

 - "\w" ou word char: Qualquer alfanumérico, ou caractere de palavra([A-Za-z0-9_]);
 - "\W" ou non-word char: o contrário de word char, ou seja, tudo que não é um word char. É um atalho para [^\w]; 
  
 - "\s" ou whitespace: Define que deseja encontrar um espaço vazio. Pode ser usado quantifier para encontrar tab colocando {1,} (um ou mais vezes) ou usando + que é o mesmo que este quantifier. Atalho para [ \t\r\n\f]:
   - "\t": Define que deseja encontrar um tab;
   - "\r": Define que deseja encontrar um carriage return;
   - "\n": Define que deseja encontrar uma new line;
   - "\f": Define que deseja encontrar um form feed;

 - Âncoras: Uma âncora não seleciona um caractere, ela seleciona apenas uma posição no texto alvo. Existem várias âncoras predefinidas, mas as mais comuns são ^, $ e \b. ^ e $ também são meta-chars. Exemplos abaixo:
   - "\b" ou word boundary: É uma das âncoras chamada de word boundary. Ou seja, a âncora \b sozinha devolve nada (zero-length), mas combinada com um valor literal, ou outro regex, podemos definir como encontrar uma preposição, como por exemplo, a preposição "de" em alguns dos exemplos abaixo. Ela sempre vai buscar a palavra inteira que não tenha nada antes ou depois, com exceção de espaços ou caracteres especiais (menos _). Por causa dela não pegar um "match" como as classes e sim uma posição específica no alvo, âncoras não podem ser combinadas com um quantifier. Exemplo: \bde\b vai pegar as preposições que contém somente o "de", ignorando espaços. Se tiver alguma outra wordchar junto desse "de" no texto procurado, ele não vai pegar. Ele busca em palavra e não numa linha/texto inteira(o);
     - "\B" ou non-word-boundary: Também chamado de o contrário de \b, significa que deve ser pega, um conjunto de caracteres que estão dentro de uma palavra, nunca no ínicio ou no fim. Exemplo: "português proporcional compor" usando a regex \Bpor\B, pegará apenas a sílaba "por" dentro da palavra "proporcional", pois é a única que tem "por" dentro da palavra e não no começo ou final
   - "^": É uma âncora que significa que precisa começar com, ou ter no começo, determinada expressão. Também, pode significar que nada pode vir antes;
   - "$": É uma âncora que significa que precisa terminar com, ou ter no final, determinada expressão. Normalmente usado junto de "^". Também, pode significar que nada pode vir depois. Um exemplo seria "^João\s+Fulano$", que pegaria o nome de alguém que começa com "João" e termina com "Fulano";

 - "()" ou Grupos: Permite a possibilidade de colocar uma regex dentro de um grupo, como por exemplo, em uma regex de data, pode existir um grupo com a regex para dia, um grupo com a regex para mês e um grupo com a regex para ano;
   - "()?" ou Grupos opcionais: Faz com que a regex inteira dentro de um grupo seja opcional e não apenas uma meta-char. Exemplo: "(de\s+)?", que faz com que qualquer preposição "de" seja opcional;
   - "?:" ou Non-capturing groups: Normalmente, vão dentro de grupos. Significa que um determinado grupo não será devolvido pela regex, ou seja, não será devolvido em tela como grupo, como por exemplo, neste Regex Engine do curso. Exemplo: "(?:de\s+)?" que faz com que qualquer preposição "de" não seja mostrada na tela como grupo;
   - "|" ou pipe: Significa que, dentro de um grupo, eu quero ou uma coisa ou outra. Exemplo: "(\d|\s)" que significa Dígito ou espaço;

 - "\nº"(nº do grupo) ou Back References: referencia um grupo que já foi usado anteriormente. Um ótimo exemplo de uso são as tags html, que mantém as tags de início e fim iguais, sem deixar o usuário colocar tags diferentes, como por exemplo, se o começo é h1, o final também tem que ser h1 e não h2;

## Alguns exemplos

João Fulano,123.456.789-00,21 de Maio de 1993,(21) 3079-9987,Rua do Ouvidor,50,20040-030,Rio de Janeiro
Maria Fulana, 98765432100,11 de Abril de 1995,(11) 933339871,Rua Vergueiro,3185,04101-300,São Paulo
denise teste, 987.654.321.00,28 de Dezembro de 1991,(31)45562712,SCS Qd. 8 Bl. B-50,11,70333-900,Rio Grande

123.456.789-00
\d{3}\.?\d{3}\.?\d{3}\.?\-?\d{2}

28      de Dezembro de 1991
[1-3]?\d\s+de\s+[A-Z][a-zç]{3,8}\s+de\s+[12]\d{3}
([0-3]?\d)\s+(?:de\s+)?([A-Z][a-zç]{3,8})\s+(?:de\s+)?([12]\d{3})

23h52min59s
(([0-2][0-3])|([0-1][0-9]))h[0-5]\dmin[0-5]\ds

9.8 - Robson, 7.1 - Teresa, 4.5 - Armênio, 6.5 - Zulu, 7.7 - Stefania, 7.8 - João, 5.0 - Romeu, 7.2 - Pompilho, 3.1 - Reinaldo, 7.3 - Bernadete, 4.7 - Cinério 
7\.[2-9]\s\-\s[A-Z][a-zçãá]+

BALEIRO GARROTE SERROTE GOLEIRO ROTEIRO
\[A-Z]*?ROT\[A-Z]+

.*/exercises/\d+/answer/\d+$
https://cursos.alura.com.br/courses/expressoes-regulares2/sections/3/exercises/15179/answer/4766568 - Esse passará na regex
https://cursos.alura.com.br/courses/expressoes-regulares2/sections/3/exercises/15179/answer/4766568/aluno - Esse não passará na regex

Z171PZ7AZ23PZ7819AZ78GZ1AZ99IZ34O
Z\d+(\w) - Mostrar Grupos

Caused by: com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure
(^Caused\s+by:\s+[\w.]*):\s+(.*)
(^Caused[\s\w:.]+):([\s\w]+) - Faz o mesmo que acima

TEAM.donkey-kong@MARIO.kart1.nintendo.com
^((?:[a-zA-Z]+[.-]?)+(?:\w+))(@[A-Za-z]+)((?:\.\w{2,4})+)$

bowser1@alura.com.br
([a-z.]{4,14}[a-z\d])@(?:alura.com.br|caelum.com.br)

Leonardo Cordeiro|01/01/1995|Rua de Campo Grande|01|00001-234|Rio de Janeiro
Romulo Henrique|14/06/1993|Rua do Lins|120|12345-322|Rio de Janeiro
Nico Steppat|14/05/1977|Rua Buarque de Macedo|50|22222-222|Rio de Janeiro
^((?:[A-Z][a-z\s]+)+)\|(?:(?:[0-2][1-9]|[3][1])/(?:[0][1-9]|[1][0-2])/(?:[1]\d{3}|[2]0[0-2][0-3]))\|((?:[A-Z][a-z\s]+)+)\|(\d{1,4})\|(\d{5}-\d{3})\|([A-Za-z\s]{10,})$ - Pegar nome, rua, número e CEP

<h1 class="text-left">Expressões regulares</h1>
<h1.+?>([\s\wõçã]+)</h1> - O quantifier de interrogação nesse serve para dar um "basta" na expressão gananciosa ".+", que normalmente iria procurar tudo até o final
<h1[^>]+>([\s\wõçã]+)</h1>

alura
[a-z]{1,5} - Gananciosa
[a-z]{1,5}? - Preguiçosa

<h1 class="text-left">Expressões regulares</h1>
<(h1|h2).+?>([\s\wõçã]+)</\1> - back reference, valida que as tags vão ser iguais

Z171PZ7AZ23PZ7819AZ78GZ1AZ99IZ34O
[^Z\d] - Qualquer coisa que não seja um Z ou um dígito, ou seja, sobrou somente as letras que formam papagaio.
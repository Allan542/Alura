regex = r'(\d\d)(\w)'
alvo = '11a22b33c'
import re
resultado = re.search(regex,alvo)
resultado.group()
# '11a'
resultado.group(0)
# '11a'
resultado.group(1)
# '11'
resultado.group(2)
# 'a'
resultado.start()
# 0
resultado.end()
# 3
resultados = re.finditer(regex,alvo)
for resultado in resultados:
    print("{0} | {1} | {2} [{3},{4}]".format(resultado.group(), resultado.group(1), resultado.group(2), resultado.start(), resultado.end()))
    #   print "%s com grupo %s [%s,%s]" % (resultado.group(), resultado.group(1),resultado.start(), resultado.end())
# ... 
# 11a | 11 | a [0,3]
# 22b | 22 | b [3,6]
# 33c | 33 | c [6,9]


# Usando findAll

import re

regex = re.compile(r'(\d\d)(\w)')
alvo = '11a22b33c'

resultado = re.findall(regex, alvo)

print(resultado)
# [('11', 'a'), ('22', 'b'), ('33', 'c')]

resultado[0]
# ('11', 'a')
resultado[1]
# ('22', 'b')
resultado[2]
# ('33', 'c')

for grupo in resultado: # Imprimindo com for
    print(grupo)
# ('11', 'a')
# ('22', 'b')
# ('33', 'c')

for grupo in resultado: # Concatenando
    print(grupo[0] + grupo[1])
# 11a
# 22b
# 33c

# Usando o método sub

import re
regex = '\s-\s'
novotexto = ': '
alura = 'Alura - Regex'
resultado = re.sub(regex, novotexto, alura)
print(resultado) # Alura: regex

import re
regex = '-'
novotexto = '/'
alvo = '2007-12-31'
resultado = re.sub(regex, novotexto, alvo)

print(resultado) # 2007/12/31
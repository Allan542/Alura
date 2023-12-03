<?php
    $regexp = '~(\d\d)(\w)~';
    $alvo = '11a22b33c';
    $achou = preg_match($regexp, $alvo, $match);
    echo $achou;
    //1
    echo $match;
    // PHP Notice:  Array to string conversion in php shell code on line 1
    // Array
    print_r($match);
    // Array
    // (
    //     [0] => 11a
    //     [1] => 11
    //     [2] => a
    // )
    $achou = preg_match_all($regexp, $alvo, $match);
    echo $achou;
    // 3
    print_r($match);
    // Array
    // (
    //     [0] => Array
    //         (
    //             [0] => 11a
    //             [1] => 22b
    //             [2] => 33c
    //         )

    //     [1] => Array
    //         (
    //             [0] => 11
    //             [1] => 22
    //             [2] => 33
    //         )

    //     [2] => Array
    //         (
    //             [0] => a
    //             [1] => b
    //             [2] => c
    //         )

    // )
    echo $match[2][2];
    // c
    $achou = preg_match_all($regexp, $alvo, $match,PREG_OFFSET_CAPTURE);
    echo $achou;
    // 3
    print_r($match);
    // Array
    // (
    //     [0] => Array
    //         (
    //             [0] => Array
    //                 (
    //                     [0] => 11a
    //                     [1] => 0
    //                 )

    //             [1] => Array
    //                 (
    //                     [0] => 22b
    //                     [1] => 3
    //                 )

    //             [2] => Array
    //                 (
    //                     [0] => 33c
    //                     [1] => 6
    //                 )

    //         )

    //     [1] => Array
    //         (
    //             [0] => Array
    //                 (
    //                     [0] => 11
    //                     [1] => 0
    //                 )

    //             [1] => Array
    //                 (
    //                     [0] => 22
    //                     [1] => 3
    //                 )

    //             [2] => Array
    //                 (
    //                     [0] => 33
    //                     [1] => 6
    //                 )

    //         )

    //     [2] => Array
    //         (
    //             [0] => Array
    //                 (
    //                     [0] => a
    //                     [1] => 2
    //                 )

    //             [1] => Array
    //                 (
    //                     [0] => b
    //                     [1] => 5
    //                 )

    //             [2] => Array
    //                 (
    //                     [0] => c
    //                     [1] => 8
    //                 )

    //         )

    // )
    echo $match[2][0][1];
    // 2
    echo $match[2][2][1];
    // 8

    $string = 'Setembro 21';
    $regex = '~(\w+)\s(\d+)~';
    $novoTexto = '$2 de $1'; // $2 e $1 são usados para referenciar o grupo, como se fossem \2 e \1

    $resultado = preg_replace($regex, $novoTexto, $string);
    echo $resultado; // 21 de Setembro

    $string = '2007-12-31';
    $regex = '~(\d+)-(\d+)-(\d+)~';
    $novoTexto = '$3-$2-$1';

    $resultado = preg_replace($regex, $novoTexto, $string);
    echo $resultado; // 31-12-2007


    $string = '31-12-2007';
    $regex = '~-~';
    $novoTexto = '/';
    $resultado = preg_replace($regex, $novoTexto, $string);

    echo $resultado; // 31/12/2007
?>

regex = r'(\d\d)(\w)'
alvo = '11a22b33c'
import re
resultado = re.search(regex,alvo)
resultado.group()

resultados = re.finditer(r'(\d\d)\w','11a22b33c')
for resultado in resultados:
     print "%s com grupo %s [%s,%s]" % (resultado.group(), resultado.group(1),resultado.start(), resultado.end())

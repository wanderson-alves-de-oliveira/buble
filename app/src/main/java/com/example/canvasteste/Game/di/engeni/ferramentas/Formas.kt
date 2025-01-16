package com.example.canvasteste.Game.di.engeni.ferramentas

import com.example.canvasteste.R
import kotlin.random.Random

class Formas() {

    val listCores = mutableListOf(
        // R.drawable.red,R.drawable.blue,R.drawable.yaelow,R.drawable.green
        R.drawable.red,
        R.drawable.blue,
        R.drawable.yaelow,
        R.drawable.pink,
        R.drawable.green
    )
    var litOffsetExtFI = mutableListOf<Int>(42, 73, 105, 136)


//
//    val flechas = mutableListOf<Int>(
//        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0,0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
//    )






    val ciculos = mutableListOf<Int>(
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 0, 1, 1, 0, 1, 1, 0, 1,
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 0, 1, 1, 0, 1, 1, 0, 1,
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0,
        1, 0, 1, 1, 0, 1, 1, 0, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    val floress = mutableListOf<Int>(
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 2, 1, 1, 2, 1, 1, 2, 1,
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,

        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 2, 1, 1, 2, 1, 1, 2, 1,
        0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0,//

        0, 1, 1, 0, 1, 1, 0, 1, 1, 0,
        0, 1, 2, 1, 1, 2, 1, 1, 2, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0,

        0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0,
        1, 2, 1, 1, 2, 1, 1, 2, 1, 0,
        0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0,

        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    val quadrado = mutableListOf<Int>(
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
        0, 0, 1, 1, 1, 1, 1, 1, 0, 0,0,
        0, 0, 2, 2, 2, 2, 2, 2, 0,0,
        0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0,
        0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0,
        0, 0, 5, 5, 5, 5, 5, 5, 0,0,
        0, 0, 6, 6, 6, 6, 6, 6, 0,0,0,
        0, 0, 7, 7, 7, 7, 7, 7, 0, 0,
        0, 0, 8, 8, 8,8, 8, 8, 8, 0,0,
        0, 0, 9, 9, 9, 9, 9, 9, 0,0,
        0, 0, 10, 10, 10, 10, 10, 10, 10, 0, 0,
        0, 0, 11, 11, 11, 11, 11, 11, 0, 0,
        0, 0, 12, 12, 12, 12, 12, 12, 12, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )


    val maze = mutableListOf<Int>(
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
        2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2,
        2, 2, 2, 2, 3, 3, 2, 2, 2, 2,
        2, 2, 2, 2, 2, 3, 3, 3, 2, 2, 2,

        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3,//

        4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4,

        5, 5, 5, 5, 4, 4, 4, 5, 5, 5, 5,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        6, 6, 6, 6, 5, 5, 6, 6, 6, 6, 6,

        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )
    val flechas = mutableListOf<Int>(
          1, 1, 1, 1, 1, 1, 1, 1, 1, 1,//null
        0, 1, 1, 1,2,2, 2, 1, 1, 1, 0,
          0, 1, 1, 2, 2, 2,2,1, 1, 0,
        0, 0,0, 1, 2,2, 2, 1, 0,0, 0,
           0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0,
            0, 0, 0, 1, 2, 1, 0, 0, 0, 0,
          0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
           0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
          0, 2, 2, 2,2, 2, 2, 2, 2, 0, 0,
        0, 0, 2, 2, 2, 2, 2, 0, 0, 0,
          0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0,
           0, 0, 0, 0, 3, 0, 0, 0, 0, 0,
        0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0,
        0, 0, 5, 5, 5, 5, 5, 0, 0, 0,
        0, 0, 0, 0, 6, 6, 6, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    fun pegarFlores(fase: Int): MutableList<MutableList<Int>> {

        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()
        var nivel = if (fase < 51) 1 else if (fase < 101) 2 else 3





        for (i in 0..floress.size - 1) {

            if (floress[i] > 0) {
                floresX.add(i)
            }
        }


        var inny = (0..4).random()
        var inny2 = if (inny + 1 > 4) 0 else inny + 1


        var inny3 = (0..4).random()
        var inny4 = if (inny3 + 1 > 4) 0 else inny3 + 1

        var inny5 = (0..4).random()
        var inny6 = if (inny5 + 1 > 4) 0 else inny5 + 1


        var inny7 = (0..4).random()
        var inny8 = if (inny7 + 1 > 4) 0 else inny7 + 1

        for (ii in 0..floress.size - 1) {
            if (nivel == 1) {
                if (ii <= 41) {
                    inny = 4
                    inny2 = 3
                } else if (ii <= 72) {
                    inny = 0
                    inny2 = 3
                } else if (ii <= 104) {
                    inny = 1
                    inny2 = 0
                } else if (ii <= 135) {
                    inny = 2
                    inny2 = 3
                }
                if (floress[ii] > 0) {
                    if (floress[ii] == 2) {
                        floresXC.add(listCores[inny2])
                    } else {
                        floresXC.add(listCores[inny])
                    }
                }

            } else if (nivel == 2) {


                if (ii <= 41) {

                    if (floress[ii] > 0) {
                        if (floress[ii] == 2) {
                            floresXC.add(listCores[inny2])
                        } else {
                            floresXC.add(listCores[inny])
                        }
                    }
                } else if (ii <= 72) {


                    if (floress[ii] > 0) {
                        if (floress[ii] == 2) {
                            floresXC.add(listCores[inny4])
                        } else {
                            floresXC.add(listCores[inny3])
                        }
                    }


                } else if (ii <= 104) {
                    if (floress[ii] > 0) {
                        if (floress[ii] == 2) {
                            floresXC.add(listCores[inny6])
                        } else {
                            floresXC.add(listCores[inny5])
                        }
                    }
                } else if (ii <= 135) {

                    if (floress[ii] > 0) {
                        if (floress[ii] == 2) {
                            floresXC.add(listCores[inny8])
                        } else {
                            floresXC.add(listCores[inny7])
                        }
                    }
                }

            } else if (nivel == 3) {


                if (ii <= 41) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 72) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 104) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 135) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                }

                if (floress[ii] > 0) {
                    if (floress[ii] == 2) {
                        floresXC.add(listCores[inny2])
                    } else {
                        floresXC.add(listCores[inny])
                    }
                }


            }


        }


        var cont: MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont
    }


    fun pegarQuadrado(fase: Int): MutableList<MutableList<Int>> {

        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()
        for (i in 0..quadrado.size - 1) {
            if (quadrado[i] > 0) {
                floresX.add(i)
            }
        }
        var inny = 0
        var inny2 = 1
        var inny3 = 2
        var inny4 = 3
        var inny5 = 4
        var inny6 = if (inny5 + 1 > 4) 0 else inny5 + 1
        var inny7 = (0..4).random()
        var inny8 = if (inny7 + 1 > 4) 0 else inny7 + 1
        for (ii in 0..quadrado.size - 1) {

            when (quadrado[ii]) {

                1  -> floresXC.add(listCores[inny])
                2  -> floresXC.add(listCores[inny2])
                3  -> floresXC.add(listCores[inny3])
                4  -> floresXC.add(listCores[inny4])
                5 -> floresXC.add(listCores[inny5])
                6 -> floresXC.add(listCores[inny6])
                7 -> floresXC.add(listCores[inny7])
                8 -> floresXC.add(listCores[inny8])
                else ->  floresXC.add(listCores[inny8])

            }
        }

            var cont: MutableList<MutableList<Int>> = mutableListOf()
            cont.add(floresX)
            cont.add(floresXC)
            return cont

    }

    fun pegarCirculos(fase: Int): MutableList<MutableList<Int>> {

        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()
        var nivel = if (fase < 51) 1 else if (fase < 101) 2 else 3





        for (i in 0..ciculos.size - 1) {

            if (ciculos[i] > 0) {
                floresX.add(i)
            }
        }


        var inny = (0..4).random()
        var inny2 = if (inny + 1 > 4) 0 else inny + 1


        var inny3 = (0..4).random()
        var inny4 = if (inny3 + 1 > 4) 0 else inny3 + 1

        var inny5 = (0..4).random()
        var inny6 = if (inny5 + 1 > 4) 0 else inny5 + 1


        var inny7 = (0..4).random()
        var inny8 = if (inny7 + 1 > 4) 0 else inny7 + 1

        for (ii in 0..ciculos.size - 1) {
            if (nivel == 1) {
                if (ii <= 41) {
                    inny = 4
                    inny2 = 3
                } else if (ii <= 72) {
                    inny = 0
                    inny2 = 3
                } else if (ii <= 104) {
                    inny = 1
                    inny2 = 0
                } else if (ii <= 135) {
                    inny = 2
                    inny2 = 3
                }
                if (ciculos[ii] > 0) {
                    if (ciculos[ii] == 2) {
                        floresXC.add(listCores[inny2])
                    } else {
                        floresXC.add(listCores[inny])
                    }
                }

            } else if (nivel == 2) {


                if (ii <= 41) {

                    if (ciculos[ii] > 0) {
                        if (ciculos[ii] == 2) {
                            floresXC.add(listCores[inny2])
                        } else {
                            floresXC.add(listCores[inny])
                        }
                    }
                } else if (ii <= 72) {


                    if (ciculos[ii] > 0) {
                        if (ciculos[ii] == 2) {
                            floresXC.add(listCores[inny4])
                        } else {
                            floresXC.add(listCores[inny3])
                        }
                    }


                } else if (ii <= 104) {
                    if (ciculos[ii] > 0) {
                        if (ciculos[ii] == 2) {
                            floresXC.add(listCores[inny6])
                        } else {
                            floresXC.add(listCores[inny5])
                        }
                    }
                } else if (ii <= 135) {

                    if (ciculos[ii] > 0) {
                        if (ciculos[ii] == 2) {
                            floresXC.add(listCores[inny8])
                        } else {
                            floresXC.add(listCores[inny7])
                        }
                    }
                }

            } else if (nivel == 3) {


                if (ii <= 41) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 72) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 104) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                } else if (ii <= 135) {

                    inny = (0..4).random()
                    inny2 = if (inny + 1 > 4) 0 else inny + 1
                }

                if (ciculos[ii] > 0) {
                    if (ciculos[ii] == 2) {
                        floresXC.add(listCores[inny2])
                    } else {
                        floresXC.add(listCores[inny])
                    }
                }


            }


        }


        var cont: MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont
    }

    fun pegarMaze(fase: Int): MutableList<MutableList<Int>> {

        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()

        var inny = (0..4).random()
        var inny2 = if (inny + 1 > 4) 0 else inny + 1


        var inny3 = (0..4).random()
        var inny4 = if (inny3 + 1 > 4) 0 else inny3 + 1

        var inny5 = (0..4).random()
        var inny6 = if (inny5 + 1 > 4) 0 else inny5 + 1


        var inny7 = (0..4).random()
        var inny8 = if (inny7 + 1 > 4) 0 else inny7 + 1

        var innyf = mutableListOf(inny, inny2, inny3, inny4, inny5, inny6, inny7, inny8)

        for (i in 0..maze.size - 1) {


            if (maze[i] > 0) {
                floresX.add(i)
            }
        }

        var nivel = if (fase < 51) 1 else if (fase < 101) 2 else 3

        for (ii in 0..maze.size - 1) {

            if (nivel == 1) {
                if (maze[ii] == 1) {
                    floresXC.add(listCores[innyf[0]])
                }


                if (maze[ii] == 2) {
                    floresXC.add(listCores[innyf[1]])
                }

                if (maze[ii] == 3) {
                    floresXC.add(listCores[innyf[2]])
                }

                if (maze[ii] == 4) {
                    floresXC.add(listCores[innyf[3]])
                }

                if (maze[ii] == 5) {
                    floresXC.add(listCores[innyf[4]])
                }

                if (maze[ii] == 6) {
                    floresXC.add(listCores[innyf[5]])
                }
            }else{

                floresXC.add(listCores[(0..4).random()])
            }
        }

        var cont: MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont
    }




    fun pegarFlecha(fase: Int): MutableList<MutableList<Int>> {

        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()

        var nivel = if (fase < 51) 1 else if (fase < 101) 2 else 3
        var inny = (0..4).random()
        var inny2 = if (inny + 1 > 4) 0 else inny + 1


        var inny3 = (0..4).random()
        var inny4 = if (inny3 + 1 > 4) 0 else inny3 + 1

        var inny5 = (0..4).random()
        var inny6 = if (inny5 + 1 > 4) 0 else inny5 + 1


        var inny7 = (0..4).random()
        var inny8 = if (inny7 + 1 > 4) 0 else inny7 + 1

        var innyf = mutableListOf(inny, inny2, inny3, inny4, inny5, inny6, inny7, inny8)

        for (i in 0..flechas.size - 1) {


            if (flechas[i] > 0) {
                floresX.add(i)
            }
        }


        for (ii in 0..flechas.size - 1) {

            if (nivel == 1) {

                if (flechas[ii] == 1) {
                    floresXC.add(listCores[innyf[0]])
                }


                if (flechas[ii] == 2) {
                    floresXC.add(listCores[innyf[1]])
                }

                if (flechas[ii] == 3) {
                    floresXC.add(listCores[innyf[2]])
                }

                if (flechas[ii] == 4) {
                    floresXC.add(listCores[innyf[3]])
                }

                if (flechas[ii] == 5) {
                    floresXC.add(listCores[innyf[4]])
                }

                if (flechas[ii] == 6) {
                    floresXC.add(listCores[innyf[5]])
                }
            }else {

                    floresXC.add(listCores[(0..4).random()])

            }
        }


        var cont: MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont
    }



    fun gerarSequencia(base: Int,interv1:Int,interv2: Int): List<Int> {



        // Configuramos o gerador com uma semente baseada no número fornecido
        val random = Random(base)

        // Gerar o tamanho da sequência entre 30 e 80
        val tamanho = random.nextInt(interv1, interv2)

        // Criar uma lista de números de 0 a 178 e embaralhá-la
        val numeros = (10..146).toMutableList().shuffled(random)

        // Selecionar os primeiros `tamanho` números da lista embaralhada
        return numeros.take(tamanho)


    }



    fun pegarMain(fase: Int): MutableList<MutableList<Int>> {


        var sequencia = gerarSequencia(fase,60,167).toMutableList()
       val incluir: List<Int> = listOf(0,1,2,3,4,5,6,7,8,9)
       sequencia.addAll(incluir)


        var floresX: MutableList<Int> = mutableListOf()
        var floresXC: MutableList<Int> = mutableListOf()
        var nivel = if (fase < 51) 4 else if (fase < 101) 8 else 33
        var index = -1


        for (i in 0..sequencia.size - 1) {

                floresX.add(sequencia[i])

        }


        var inny = (0..4).random()


        for (ii in 0..sequencia.size - 1) {

                    inny = (0..4).random()
                    floresXC.add(listCores[inny])


        }


        var cont: MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont
    }

}
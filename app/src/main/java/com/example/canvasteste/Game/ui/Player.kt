package com.example.canvasteste.Game.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.logic.AAbilite
import com.example.canvasteste.Game.logic.CCores
import com.example.canvasteste.Game.logic.CCoresSeparacao
import com.example.canvasteste.Game.logic.PlayLogic
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.R
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
internal fun Player(

    modifier: Modifier,
    playerLogic: PlayLogic,
    abilite: AAbilite,
    cores: CCores,
    coresSeparacao: CCoresSeparacao,
    viewport: Viewport,
    tela: Tela,
    navController: NavController,
    fase: String
) {

    var tocar: Boolean by remember { mutableStateOf(true) }

    val player = playerLogic.player.collectAsState()
    val ab = abilite.Abilite.collectAsState()
    val cr = cores.Cores.collectAsState()
    val cs = coresSeparacao.Cores.collectAsState()
    val off = tela.getTamanhoTela()
    var ii: Float = (viewport.width / 2).toPx()
    var offsetX: Float by remember { mutableStateOf(ii) }
    var offsetX2: Float by remember { mutableStateOf(ii) }
    var offsetX3: Float by remember { mutableStateOf(ii) }
    var offsetY by remember { mutableStateOf(off.y * 0.9) }
    val listCores = cr.value.listCores
    var intPreviewCor: Int by remember { mutableStateOf(listCores[(0..3).random()]) }
    var intPreviewCor2: Int by remember { mutableStateOf(listCores[(0..3).random()]) }
    var pointer by remember { mutableStateOf(Offset(0f, 0f)) }

    var up: Dp by remember { mutableStateOf(1000.dp) }
    var rotation: Float by remember { mutableStateOf(-90f) }
    var intPreview: Int by remember { mutableStateOf(0) }
    var intPreviewMarcado: Int by remember { mutableStateOf(0) }
    var listPrev: List<Int> = player.value.posprev
    var subir: Boolean by remember { mutableStateOf(false) }
    var listaCoresOff: MutableList<Int> = cs.value.cores
    var listaCorteRamos: MutableList<Int> = ab.value.posRamo
    var litOffsetExt: MutableList<Int> = ab.value.pos
    litOffsetExt.sort()
    var listCoresExt: MutableList<Int> = cr.value.cores
    var qtd: Int = 30
    var unidadeT = (off.y * 0.9) / qtd
    var telaw = (off.x)
    var xi: Int = (((offsetX - ii) / qtd)).toInt()
    val posRef: Offset3 = ab.value.posRef
    var i = qtd
    var go: Boolean by remember { mutableStateOf(false) }
    var xxiu = (ii + (xi * (qtd))).toInt()
    var xc = if (i == qtd) xxiu - ((30 / 2) / 2) else xxiu
    var dir: Float by remember { mutableStateOf(70f) }
    var xxPlay: Float by remember { mutableStateOf(xc.toFloat()) }
    var tocou: Boolean by remember { mutableStateOf(false) }
    var fim: Boolean by remember { mutableStateOf(false) }
    var modo: Int by remember { mutableStateOf(0) }
    var ultimaLinha: Int = ab.value.ultimaLinha
    var yyPlay: Float by remember { mutableStateOf((qtd * unidadeT).toFloat()) }
    var litOffset2 = mutableListOf<Offset>()
    var litORef = mutableListOf<Offset3>(
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1)
    )
    var litOffsetExtFI = mutableListOf<Int>(10, 31, 52, 73, 94, 115, 136, 157)
    var litOffsetExtFI2 = mutableListOf<Int>(21, 42, 63, 84, 105, 126, 147, 168)

    var lt = mutableListOf<Offset3>()
    var valorinicio: Boolean by remember { mutableStateOf(false) }
    var valorinicioTotal: Boolean by remember { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    if (subir && up > 0.dp) {
        up -= if (up > 10.dp) (up / 10.dp).dp else 10.dp
    }
    val posMoveReset: MutableList<Offset3> = ab.value.posMoveReset

    var litOffsetMove = ab.value.posMove

        if (!fim) {



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(-10.dp, up)
                    .pointerInput(Unit) {
                        var interaction: DragInteraction.Start? = null
                        detectDragGestures(
                            onDragStart = {
                                coroutineScope.launch {
                                    interaction = DragInteraction.Start()
                                    interaction?.run {
                                        interactionSource.emit(this)
                                    }
                                }
                            },
                            onDrag = { change: PointerInputChange, dragAmount: Offset ->
                                if (up <= 0.dp) {

                                        offsetX += dragAmount.x * 7
                                        offsetX2 += dragAmount.x * 7
                                        offsetX3 += dragAmount.x * 7
                                        offsetY += dragAmount.y


                                    tocou = true
                                    if (dragAmount.x > 0 && rotation < 0) {
                                        rotation += 3
                                    } else if (dragAmount.x < 0 && rotation > -180) {
                                        rotation -= 3
                                    }
                                }
                            },
                            onDragCancel = {
                                coroutineScope.launch {
                                    interaction?.run {
                                        interactionSource.emit(DragInteraction.Cancel(this))
                                        tocou = false
                                        up = 0.dp

                                    }
                                }
                            },
                            onDragEnd = {
                                coroutineScope.launch {
                                    interaction?.run {

                                        if (intPreviewMarcado != intPreview || (litOffsetExtFI.contains(
                                                intPreviewMarcado
                                            ) || litOffsetExtFI2.contains(intPreviewMarcado))
                                        ) {
                                            go = true
                                            tocou = false

                                            playerLogic.OnZom()

                                        }

                                        interactionSource.emit(DragInteraction.Stop(this))
                                    }
                                }
                            }
                        )


                    }
                    .pointerInput(Unit) {


                        var interaction: DragInteraction.Start? = null
                        detectTapGestures { tapOffset ->
                            coroutineScope.launch {
                                interaction = DragInteraction.Start()
                                interaction?.run {
                                    interactionSource.emit(this)

                                    pointer = Offset(tapOffset.x, tapOffset.y)

                                    if (pointer.x.toDp() >= 175.dp && pointer.x.toDp() <= 253.dp &&
                                        pointer.y.toDp() >= 700.dp && pointer.y.toDp() <= 760.dp
                                    ) {

                                        var aux = intPreviewCor
                                        intPreviewCor = intPreviewCor2
                                        intPreviewCor2 = aux

                                    }
                                }

                            }
                        }

                    }
            ) {



                    ///////////////////////////////////////////

                    if (!(tocou || go)) i = qtd

                    while (i > 0) {

                        var mostrar = true
                        val coroutineScope2 = rememberCoroutineScope()
                        coroutineScope2.run {



                        var xxi = (ii + (xi * (qtd - i))).toInt()
                        if (xxi <= 0) {
                            xxi *= -1
                            litORef[0].x = xxi.toFloat()
                            litORef[0].y = (i * unidadeT).toFloat()
                            litORef[0].vazio = false
                            litORef[0].pos = 0
                        }
                        if (xxi >= telaw) {
                            xxi = ((xxi * -1) + (telaw * 2)).toInt()
                            litORef[0].x = xxi.toFloat()
                            litORef[0].y = (i * unidadeT).toFloat()
                            litORef[0].vazio = false
                            litORef[0].pos = 0
                        }
                        if (xxi <= 0) {
                            xxi *= -1
                            litORef[1].x = xxi.toFloat()
                            litORef[1].y = (i * unidadeT).toFloat()
                            litORef[1].vazio = false
                            litORef[1].pos = 1
                        }
                        if (xxi >= telaw) {
                            xxi = ((xxi * -1) + (telaw * 2)).toInt()
                            litORef[1].x = xxi.toFloat()
                            litORef[1].y = (i * unidadeT).toFloat()
                            litORef[1].vazio = false
                            litORef[1].pos = 1
                        }
                        if (xxi <= 0) {
                            xxi *= -1
                            litORef[2].x = xxi.toFloat()
                            litORef[2].y = (i * unidadeT).toFloat()
                            litORef[2].vazio = false
                            litORef[2].pos = 2
                        }
                        if (xxi >= telaw) {
                            xxi = ((xxi * -1) + (telaw * 2)).toInt()
                            litORef[2].x = xxi.toFloat()
                            litORef[2].y = (i * unidadeT).toFloat()
                            litORef[2].vazio = false
                            litORef[2].pos = 2
                        }
                        var s = 10.dp
                        var xc = if (i == qtd) xxi - ((s / 2) / 2).toPx() else xxi
                        var yc = (i * unidadeT).toInt()
                        if (yc <= 0) yc = 0
                        if (i == qtd || (yyPlay.toInt() < yc && yyPlay.toInt() >= yc - 30.dp.toPx())) {
                            s = 36.dp
                            xc = if (i == qtd) xxi - ((s / 2) / 2).toPx() else xxi
                        }
                        litOffset2.add(Offset(xc.toFloat(), yc.toFloat()))

                        if (subir) {
                            for (j in 0..litOffsetMove.size - 1) {
                                if (!litOffsetMove[j].vazio) {
                                    var m1 = (litOffsetMove[j].x - xc.toFloat()).pow(2)
                                    var m2 = (litOffsetMove[j].y - yc.toFloat()).pow(2)
                                    var d: Float = sqrt((m1 + m2))
                                    if (d < 30.dp.toPx()) {
                                        mostrar = false
                                        var litOffset3 = mutableListOf<Offset3>()
                                        var lado = j
                                        try {
                                            if (!litOffsetExtFI.contains(j + 1) && !litOffsetExtFI2.contains(
                                                    j + 1
                                                )
                                            ) {
                                                litOffset3.add(litOffsetMove[j + 1])
                                            }
                                        } catch (e: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j) && !litOffsetExtFI2.contains(
                                                    j
                                                )
                                            ) {
                                                litOffset3.add(litOffsetMove[j - 1])
                                            }
                                        } catch (e: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j)) {
                                                litOffset3.add(litOffsetMove[j + 10])
                                            }
                                        } catch (e: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j + 11)) {
                                                litOffset3.add(litOffsetMove[j + 11])
                                            }
                                        } catch (e: Exception) {
                                        }
                                        var litOffset4 =
                                            litOffset3.filter { it -> it.vazio == true }
                                        if (litOffset4.size > 0) {
                                            for (k in 0..litOffset4.size - 1) {
                                                var posArray =
                                                    if (litOffset2.size > 2) litOffset2.size - 2 else 0
                                                var m3 =
                                                    (litOffset4[k].x - litOffset2[posArray].x).pow(2)
                                                var m4 =
                                                    (litOffset4[k].y - litOffset2[posArray].y).pow(2)
                                                var d2: Float = sqrt((m3 + m4))
                                                if (d2 < 30.dp.toPx()) {
                                                    lado = litOffsetMove.indexOf(litOffset4[k])

                                                    break
                                                }
                                            }
                                        }
                                        intPreviewMarcado = j
                                        if (j < 1200 && !go) {
                                            intPreview = lado
                                        }
                                        var mesmaCorf =
                                            litOffsetExt.filter { it ->
                                                listCoresExt[litOffsetExt.indexOf(
                                                    it
                                                )] == intPreviewCor
                                            }
                                        playerLogic.updatePrev(listaCoresOff)
                                        listaCoresOff =
                                            playerLogic.updateLimparnit(
                                                litOffsetMove,
                                                mesmaCorf,
                                                lado
                                            )
                                        break
                                    }
                                }
                            }
                        }

                        if (tocou || go || i == qtd) {
                            Image(
                                painterResource(id = intPreviewCor),
                                contentDescription = null,
                                modifier = modifier
                                    .offset {
                                        IntOffset(
                                            x = xc.toInt(),
                                            y = yc.toInt()
                                        )
                                    }
                                    .size(s)
                                    .graphicsLayer {
                                        rotationZ = rotation//(op * 90f).coerceIn(-60f, 60f)
                                    }
                            )
                        }

                        if (i == qtd) {
                            Image(
                                painterResource(id = intPreviewCor2),
                                contentDescription = null,
                                modifier = modifier
                                    .offset {
                                        IntOffset(
                                            x = (xc.toInt() + 35.dp.toPx()).toInt(),
                                            y = (yc + 20.dp.toPx()).toInt()
                                        )
                                    }
                                    .size(s * 0.7f)
                                    .graphicsLayer {
                                        rotationZ = rotation//(op * 90f).coerceIn(-60f, 60f)
                                    }
                            )
                        }



                        i--


                    }

                        if (!mostrar) {
                            break
                        }

                        //////////////////////////////////////
                    }




//                        LaunchedEffect(Unit) {
//
//                        }

                    for (i in 0..178) {

//
//                        LaunchedEffect(Unit) {
//
//                        }


                        val coroutineScope3= rememberCoroutineScope()
                        coroutineScope3.run {

                        if (litOffsetExt.contains(i)) {
                            litOffsetMove[i].vazio = false
                        }
                        var litOffsetMoveR = litOffsetMove[i]
                        if (i == 0) {
                            valorinicio = true
                        }
                        if (modo > -1 && valorinicio) {
                            var velocidade = 36.dp.toPx()
                            var difLinha =
                                (ultimaLinha - litOffsetMove[litOffsetExt[litOffsetExt.lastIndex]].linha) + litOffsetMove[i].linha
                            if (modo == 0 && litOffsetMove[i].y > 300.dp.toPx() + (difLinha * velocidade)) {
                                litOffsetMove[i].y -= velocidade
                                offsetX2 -= 0.001f
                            } else if (litOffsetMove[0].y < velocidade * 8 && modo == 1 && difLinha > 0 && litOffsetMove[i].y < posRef.y + (difLinha * velocidade)) {
                                litOffsetMove[i].y += velocidade
                                offsetX2 -= 0.001f
                            } else {
                                modo = -1
                                litOffsetMove[0].y = litOffsetMove[1].y
                            }
                            //  abilite.onUpdateMove(litOffsetMove)
                        }

                        if (listaCorteRamos.contains(i)) {
                            var op = 20.dp.toPx().toInt()

                            if (litOffsetMove[i].y >= 700.dp.toPx()) {

                                if (litOffsetExt.indexOf(i) != -1) {
                                    listCoresExt.removeAt(litOffsetExt.indexOf(i))
                                }
                                if (listaCorteRamos.indexOf(i) != -1) {
                                    listaCorteRamos.removeAt(listaCorteRamos.indexOf(i))
                                }
                                if (litOffsetExt.indexOf(i) != -1) {
                                    litOffsetExt.removeAt(litOffsetExt.indexOf(i))
                                }
                                cores.onUpdate(listCoresExt)
                                abilite.onUpdate(litOffsetExt)
                                abilite.onUpdateRamos(listaCorteRamos)

                                playerLogic.OnTocarEfeito(listaCorteRamos.size)


                            } else {
                                litOffsetMove[i].y += op
                                offsetX2 -= 0.001f
                            }
                            if (listaCorteRamos.size == 0) {
                                valorinicio = false

                                if (litOffsetExt.size > 1 && litOffsetMove[litOffsetExt[litOffsetExt.lastIndex]].y > 300.dp.toPx()) {
                                    modo = 0
                                } else if (litOffsetExt.size > 1 && litOffsetMove[litOffsetExt[litOffsetExt.lastIndex]].y < 300.dp.toPx()) {
                                    modo = 1
                                }
                                posMoveReset.sortBy { it.pos }
                                abilite.onUpdateUltimaLinha(posMoveReset[posMoveReset.lastIndex].linha)
                                for (iu in 0..posMoveReset.size - 1) {
                                    var move: Offset3 = posMoveReset[iu]
                                    litOffsetMove[move.pos] = move
                                }
                                if (posMoveReset.size > 0) {
                                    var obj =
                                        Offset3(
                                            0f + litOffsetMove[0].x,
                                            0f + litOffsetMove[0].y,
                                            false,
                                            0
                                        )
                                    abilite.onUpdatePosRef(obj)
                                }
                                posMoveReset.clear()
                                if (litOffsetExt.size < 11) {
                                    fim = true
                                    subir = false
                                    offsetX2 -= 0.001f
                                }
                            }
                            //   abilite.onUpdateMove(litOffsetMove)
                        }
                        abilite.onUpdateMove(litOffsetMove)


                        var id = R.drawable.car
                        var image = R.drawable.car
                        if (litOffsetExt.contains(i)) {
                            var corT: Int = listCoresExt[litOffsetExt.indexOf(i)]
                            id = corT
                        }

                        if (!litOffsetMoveR.vazio) {
                            image = id
                        }
                        if (i <= 9) {
                            image = R.drawable.car
                        }

                        if ((listaCorteRamos.contains(i)// ||
                                    //   listaCoresOff.contains(i) ||
                                    //  (i > 9 && listPrev.contains(i) && tocou)   ||
                                    || (i == intPreview && tocou)
                                    ) && i > 9
                        ) {
                            image = R.drawable.tranp
                        }


                        var limite = 10.dp.toPx()
                        if ((!litOffsetMoveR.vazio || i == intPreview) && litOffsetMoveR.y > limite && litOffsetMoveR.y < tela.getTamanhoTela().y - (limite * 15)) {
                            Image(
                                painterResource(id = image),
                                contentDescription = null,
                                modifier = modifier
                                    .offset {
                                        IntOffset(
                                            x = litOffsetMoveR.x.toInt(),
                                            y = litOffsetMoveR.y.toInt()
                                        )
                                    }
                                    .size(36.dp)
                                    .graphicsLayer {
                                        rotationZ = i.toFloat()//(op * 90f).coerceIn(-60f, 60f)
                                    }
                            )
                        }
                        var yc = (qtd * unidadeT).toInt()
                        Image(
                            painterResource(id = R.drawable.arcos),
                            contentDescription = null,
                            modifier = modifier
                                .offset {
                                    IntOffset(
                                        x = ((tela.getTamanhoTela().x / 2) - 21.dp.toPx()).toInt(),
                                        y = (yc - 20.dp.toPx()).toInt()
                                    )
                                }
                                .size(80.dp)
                                .graphicsLayer {
                                    rotationZ = -360f//(op * 90f).coerceIn(-60f, 60f)
                                }
                        )


//                 Text(i.toString(), modifier = modifier
//                     .offset {
//                        IntOffset(
//                             x = litOffsetMoveR.x.toInt(),
//                             y = litOffsetMoveR.y.toInt()
//                         )
//                    })


//                        Canvas(modifier = Modifier.fillMaxSize()) {
//                            drawContext.canvas.nativeCanvas.apply {
//                                drawText(
//                                    "  ${pointer.x.toDp()} ${pointer.y.toDp()}",
//                                    0f,
//                                    220f,
//                                    android.graphics.Paint().apply {
//                                        color = android.graphics.Color.WHITE
//                                        textSize = 20.sp.toPx()
//                                    }
//                                )
//                            }
//                            drawCircle(
//                                color = Color(80, 89, 196, 255),
//                                radius = 50f,
//                                center = Offset(pointer.x, pointer.y)
//                            )
//
//                        }

                    }

                    }


                    if(valorinicioTotal){
                        go = true
                        tocou = false
                        valorinicioTotal = false
                    }





                if (go) {
                    val coroutineScope6 = rememberCoroutineScope()
                    coroutineScope6.run {

                        var lpp = mutableListOf(0)
                        coresSeparacao.onUpdate(lpp)
                        lt = player.value.posRebote
                        dir =
                            ((sqrt(
                                (((offsetX - xxPlay).dp.toPx()).pow(2)) + ((offsetY - yyPlay).pow(
                                    2
                                ))
                            )) / 10).toFloat()
                        if (lt[0].y < yyPlay) {
                            yyPlay -= 50.dp.toPx()
                            xxPlay = 500f
                        }
                        if (yyPlay <= lt[0].y) {
                            go = false
                            offsetX =0f
                            offsetX2 =0f
                            offsetX3 =0f
                            offsetY =0.0
                            litOffsetExt.add(intPreview)
                            litOffsetExt.sort()
                            litOffsetExt = removendoDuplicados(litOffsetExt)
                            listCoresExt.add(litOffsetExt.indexOf(intPreview), intPreviewCor)
                            listaCoresOff = listaCoresOff.filter { it -> it > 9 }.toMutableList()
                            if (listaCoresOff.size == 1 && listaCoresOff[0] == 0) {
                                var mesmaCorf =
                                    litOffsetExt.filter { it -> listCoresExt[litOffsetExt.indexOf(it)] == intPreviewCor }
                                listaCoresOff =
                                    playerLogic.updateLimparnit(
                                        litOffsetMove,
                                        mesmaCorf,
                                        intPreview
                                    )
                            }


                            listaCorteRamos = playerLogic.updateLimparRamosinit(
                                listaAtual = litOffsetMove,
                                posL = litOffsetExt,
                                init = 0
                            )
                            var listd =
                                litOffsetExt.filter { it -> !listaCorteRamos.contains(it) } as MutableList<Int>
                            var lista1 = listd.filter { it -> !listaCoresOff.contains(it) }
                            var listRA: MutableList<Int> = listaCoresOff
                            listRA.addAll(lista1)
                            var novalista: MutableList<Int> =
                                litOffsetExt.filter { it -> !listRA.contains(it) } as MutableList<Int>
                            var listaCorteRamosNovo = playerLogic.updateLimparRamosinit(
                                listaAtual = litOffsetMove,
                                posL = novalista,
                                init = 0
                            )

                            for (iy in 0..listaCoresOff.size - 1) {
                                if (listaCoresOff.size < 3) break
                                litOffsetMove[listaCoresOff[iy]].vazio = false


                                novalista =
                                    litOffsetExt.filter { it -> !listaCorteRamosNovo.contains(it) }
                                        .toMutableList()

                                var incluirRemovidosLeft =
                                    listRA.filter { it -> !novalista.contains(it) }

                                novalista.addAll(incluirRemovidosLeft)
                                var rest: MutableList<Offset3> = mutableListOf()
                                for (iu in 0..novalista.size - 1) {
                                    var move: Offset3 = litOffsetMove[novalista[iu]]
                                    var o: Offset3 =
                                        Offset3(move.x + 0, move.y + 0, true, move.pos, move.linha)
                                    rest.add(o)
                                }
                                var listakkk = litOffsetMove.filter { it -> it.vazio == false }
                                var listakkk2 =
                                    listakkk.filter { it -> !litOffsetExt.contains(it.pos) }
                                for (t in 0..listakkk2.size - 1) {
                                    litOffsetMove[listakkk2[t].pos].vazio = true
                                }
                                if (listakkk2.size > 0) {
                                    abilite.onUpdateMove(litOffsetMove)
                                }
                                novalista.sort()
                                abilite.onUpdateMoveReset(rest)

                                abilite.onUpdateRamos(novalista)
                            }

                            playerLogic.OnDim()

                            var newListCor = playerLogic.verificarCorPresente(listCoresExt)
                            intPreviewCor = intPreviewCor2

                            intPreviewCor2 = newListCor[(0..newListCor.size - 1).random()]
                            cores.onUpdate(listCoresExt)
                            abilite.onUpdate(litOffsetExt)
                            xxPlay = 0f
                            yyPlay = ((qtd * unidadeT).toFloat())
                            xxPlay = (telaw / 2) - 15.dp.toPx()
                            yyPlay = ((qtd * unidadeT).toFloat())
                        }
                    }
                    tocou = false
                    //////////////////////////////////
                }
            }
            if (!subir) {

               Thread.sleep(100)

                subir = true

            }

        } else {

            playerLogic.OnFim(tocar)
            tocar = false
            Column(
                modifier = modifier
                    .size(250.dp, 310.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val coroutineScope7 = rememberCoroutineScope()
                coroutineScope7.run {
                    offsetX2 -= 0.001f

                    val res = tela.context.resources
                 //   var restMedia = 300.dp.toPx()
                    var b = BitmapFactory.decodeResource(res, R.drawable.blue)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        CardFim(b, tela.context, modifier = modifier, "", fase, onclick = {

                            playerLogic.OnMusica(false)
                         //   navController.navigate("mapa")

                            var initnew = fase.toInt()+1
                            navController.navigate("game/${initnew}")


                        })
                    }
                }

                //////////////////
            }
        }


    //////////////////////////////
}

@Composable
fun removendoDuplicados(litOffsetExt: MutableList<Int>): MutableList<Int> {
    var litOffsetExtAux = litOffsetExt
    var litOffsetExtAuxCop: MutableList<Int> = litOffsetExt.cop()
    var litOffsetExtAuxCopR: MutableList<Int> = mutableListOf()



    for (i in 1..litOffsetExt.size - 1) {

            if (litOffsetExtAux[i] == litOffsetExt[i - 1]) {
                litOffsetExtAuxCopR.add(litOffsetExtAux[i])
            }
        }


    if (litOffsetExtAuxCopR.size > 0) {
        for (j in 0..litOffsetExtAuxCopR.size - 1) {




            try {
                litOffsetExtAuxCop.removeAt(litOffsetExtAuxCopR[j])
            } catch (e: Exception) {
                Log.e("WWW", e.stackTrace.toString())
            }
            }

        }



    return litOffsetExtAuxCop
}

fun MutableList<Int>.cop(): MutableList<Int> {
  //  var litOffsetExtAux = this
    var litOffsetExtAuxCop: MutableList<Int> = mutableListOf()
    for (i in 0..this.size - 1) {
        litOffsetExtAuxCop.add(this[i] + 0)
    }
    return litOffsetExtAuxCop
}
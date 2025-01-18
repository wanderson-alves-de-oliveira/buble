package com.example.canvasteste.game.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.canvasteste.game.di.engeni.ferramentaUx.Offset3
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela
import com.example.canvasteste.game.logic.AAbilite
import com.example.canvasteste.game.logic.CCores
import com.example.canvasteste.game.logic.CCoresSeparacao
import com.example.canvasteste.game.logic.PlayLogic
import com.example.canvasteste.game.model.Viewport
import com.example.canvasteste.R
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@SuppressLint("SuspiciousIndentation")
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
    val ab = abilite.abilite.collectAsState()
    val cr = cores.kores.collectAsState()
    val cs = coresSeparacao.coresStateFlow.collectAsState()
    val off = tela.getTamanhoTela()
    val ii: Float = (viewport.width / 2).toPx()
    var offsetX: Float by remember { mutableFloatStateOf(ii) }
    var offsetX2: Float by remember { mutableFloatStateOf(ii) }
    var offsetX3: Float by remember { mutableFloatStateOf(ii) }
    var offsetY by remember { mutableDoubleStateOf(off.y * 0.9) }
    val listCores = cr.value.listCores
    var intPreviewCor: Int by remember { mutableIntStateOf(listCores[(0..3).random()]) }
    var intPreviewCor2: Int by remember { mutableIntStateOf(listCores[(0..3).random()]) }
    var pointer by remember { mutableStateOf(Offset(0f, 0f)) }

    var up: Dp by remember { mutableStateOf(1000.dp) }
    var rotation: Float by remember { mutableFloatStateOf(-90f) }
    var intPreview: Int by remember { mutableIntStateOf(0) }
    var intPreviewMarcado: Int by remember { mutableIntStateOf(0) }
    player.value.posprev
    var subir: Boolean by remember { mutableStateOf(false) }
    var listaCoresOff: MutableList<Int> = cs.value.cores
    var listaCorteRamos: MutableList<Int> = ab.value.posRamo
    var litOffsetExt: MutableList<Int> = ab.value.pos
    litOffsetExt.sort()
    val listCoresExt: MutableList<Int> = cr.value.cores
    val qtd = 30
    val unidadeT = (off.y * 0.9) / qtd
    val telaw = (off.x)
    val xi: Int = (((offsetX - ii) / qtd)).toInt()
    val posRef: Offset3 = ab.value.posRef
    var i = qtd
    var go: Boolean by remember { mutableStateOf(false) }
    val xxiu = (ii + (xi * (qtd))).toInt()
    val xc =  xxiu - ((30 / 2) / 2)
    var dir: Float by remember { mutableFloatStateOf(70f) }
    var xxPlay: Float by remember { mutableFloatStateOf(xc.toFloat()) }
    var tocou: Boolean by remember { mutableStateOf(false) }
    var fim: Boolean by remember { mutableStateOf(false) }
    var modo: Int by remember { mutableIntStateOf(0) }
    val ultimaLinha: Int = ab.value.ultimaLinha
    var yyPlay: Float by remember { mutableFloatStateOf((qtd * unidadeT).toFloat()) }
    val litOffset2 = mutableListOf<Offset>()
    val litORef = mutableListOf(
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1)
    )
    val litOffsetExtFI = mutableListOf(10, 31, 52, 73, 94, 115, 136, 157)
    val litOffsetExtFI2 = mutableListOf(21, 42, 63, 84, 105, 126, 147, 168)

    var lt: MutableList<Offset3>
    var valorinicio: Boolean by remember { mutableStateOf(false) }
    var valorinicioTotal: Boolean by remember { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    if (subir && up > 0.dp) {
        up -= if (up > 10.dp) (up / 10.dp).dp else 10.dp
    }
    val posMoveReset: MutableList<Offset3> = ab.value.posMoveReset

    val litOffsetMove = ab.value.posMove

        if (!fim) {



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset {
                        IntOffset(
                            x = -10.dp.toPx().toInt(),
                            y = up.toPx().toInt()
                        )
                    }

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
                            onDrag = { _: PointerInputChange, dragAmount: Offset ->
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

                                            playerLogic.onZom()

                                        }

                                        interactionSource.emit(DragInteraction.Stop(this))
                                    }
                                }
                            }
                        )


                    }
                    .pointerInput(Unit) {


                        var interaction: DragInteraction.Start?
                        detectTapGestures { tapOffset ->
                            coroutineScope.launch {
                                interaction = DragInteraction.Start()
                                interaction?.run {
                                    interactionSource.emit(this)

                                    pointer = Offset(tapOffset.x, tapOffset.y)

                                    if (pointer.x.toDp() >= 175.dp && pointer.x.toDp() <= 253.dp &&
                                        pointer.y.toDp() >= 700.dp && pointer.y.toDp() <= 760.dp
                                    ) {

                                        val aux = intPreviewCor
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
                        var xcux = if (i == qtd) xxi - ((s / 2) / 2).toPx() else xxi
                        var yc = (i * unidadeT).toInt()
                        if (yc <= 0) yc = 0
                        if (i == qtd || (yyPlay.toInt() < yc && yyPlay.toInt() >= yc - 30.dp.toPx())) {
                            s = 36.dp
                            xcux = if (i == qtd) xxi - ((s / 2) / 2).toPx() else xxi
                        }
                        litOffset2.add(Offset(xcux.toFloat(), yc.toFloat()))

                        if (subir) {
                            for (j in 0..<litOffsetMove.size) {
                                if (!litOffsetMove[j].vazio) {
                                    val m1 = (litOffsetMove[j].x - xcux.toFloat()).pow(2)
                                    val m2 = (litOffsetMove[j].y - yc.toFloat()).pow(2)
                                    val d: Float = sqrt((m1 + m2))
                                    if (d < 30.dp.toPx()) {
                                        mostrar = false
                                        val litOffset3 = mutableListOf<Offset3>()
                                        var lado = j
                                        try {
                                            if (!litOffsetExtFI.contains(j + 1) && !litOffsetExtFI2.contains(
                                                    j + 1
                                                )
                                            ) {
                                                litOffset3.add(litOffsetMove[j + 1])
                                            }
                                        } catch (_: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j) && !litOffsetExtFI2.contains(
                                                    j
                                                )
                                            ) {
                                                litOffset3.add(litOffsetMove[j - 1])
                                            }
                                        } catch (_: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j)) {
                                                litOffset3.add(litOffsetMove[j + 10])
                                            }
                                        } catch (_: Exception) {
                                        }
                                        try {
                                            if (!litOffsetExtFI.contains(j + 11)) {
                                                litOffset3.add(litOffsetMove[j + 11])
                                            }
                                        } catch (_: Exception) {
                                        }
                                        val litOffset4 =
                                            litOffset3.filter { it.vazio }
                                        if (litOffset4.isNotEmpty()) {
                                            for (k in litOffset4.indices) {
                                                val posArray =
                                                    if (litOffset2.size > 2) litOffset2.size - 2 else 0
                                                val m3 =
                                                    (litOffset4[k].x - litOffset2[posArray].x).pow(2)
                                                val m4 =
                                                    (litOffset4[k].y - litOffset2[posArray].y).pow(2)
                                                val d2: Float = sqrt((m3 + m4))
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
                                        val mesmaCorf =
                                            litOffsetExt.filter {
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
                                            x = xcux.toInt(),
                                            y = yc
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
                                            x = (xcux.toInt() + 35.dp.toPx()).toInt(),
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

                    for (iux in 0..178) {

//
//                        LaunchedEffect(Unit) {
//
//                        }


                        val coroutineScope3= rememberCoroutineScope()
                        coroutineScope3.run {

                        if (litOffsetExt.contains(iux)) {
                            litOffsetMove[iux].vazio = false
                        }
                            val litOffsetMoveR = litOffsetMove[iux]
                        if (iux == 0) {
                            valorinicio = true
                        }
                        if (modo > -1 && valorinicio) {
                            val velocidade = 36.dp.toPx()
                            val difLinha =
                                (ultimaLinha - litOffsetMove[litOffsetExt[litOffsetExt.lastIndex]].linha) + litOffsetMove[iux].linha
                            if (modo == 0 && litOffsetMove[iux].y > 300.dp.toPx() + (difLinha * velocidade)) {
                                litOffsetMove[iux].y -= velocidade
                                offsetX2 -= 0.001f
                            } else if (litOffsetMove[0].y < velocidade * 8 && modo == 1 && difLinha > 0 && litOffsetMove[iux].y < posRef.y + (difLinha * velocidade)) {
                                litOffsetMove[iux].y += velocidade
                                offsetX2 -= 0.001f
                            } else {
                                modo = -1
                                litOffsetMove[0].y = litOffsetMove[1].y
                            }
                            //  abilite.onUpdateMove(litOffsetMove)
                        }

                        if (listaCorteRamos.contains(iux)) {
                            val op = 20.dp.toPx().toInt()

                            if (litOffsetMove[iux].y >= 700.dp.toPx()) {

                                if (litOffsetExt.indexOf(iux) != -1) {
                                    listCoresExt.removeAt(litOffsetExt.indexOf(iux))
                                }
                                if (listaCorteRamos.indexOf(iux) != -1) {
                                    listaCorteRamos.removeAt(listaCorteRamos.indexOf(iux))
                                }
                                if (litOffsetExt.indexOf(iux) != -1) {
                                    litOffsetExt.removeAt(litOffsetExt.indexOf(iux))
                                }
                                cores.onUpdate(listCoresExt)
                                abilite.onUpdate(litOffsetExt)
                                abilite.onUpdateRamos(listaCorteRamos)

                                playerLogic.OnTocarEfeito(listaCorteRamos.size)


                            } else {
                                litOffsetMove[iux].y += op
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
                                for (iu in 0..<posMoveReset.size) {
                                    val move: Offset3 = posMoveReset[iu]
                                    litOffsetMove[move.pos] = move
                                }
                                if (posMoveReset.size > 0) {
                                    val obj =
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
                        if (litOffsetExt.contains(iux)) {
                            val corT: Int = listCoresExt[litOffsetExt.indexOf(iux)]
                            id = corT
                        }

                        if (!litOffsetMoveR.vazio) {
                            image = id
                        }
                        if (iux <= 9) {
                            image = R.drawable.car
                        }

                        if ((listaCorteRamos.contains(iux)// ||
                                    //   listaCoresOff.contains(iux) ||
                                    //  (iux > 9 && listPrev.contains(iux) && tocou)   ||
                                    || (iux == intPreview && tocou)
                                    ) && iux > 9
                        ) {
                            image = R.drawable.tranp
                        }


                            val limite = 10.dp.toPx()
                        if ((!litOffsetMoveR.vazio || iux == intPreview) && litOffsetMoveR.y > limite && litOffsetMoveR.y < tela.getTamanhoTela().y - (limite * 15)) {
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
                                        rotationZ = iux.toFloat()//(op * 90f).coerceIn(-60f, 60f)
                                    }
                            )
                        }
                            val yc = (qtd * unidadeT).toInt()
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


//                 Text(iux.toString(), modifier = modifier
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

                        val lpp = mutableListOf(0)
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
                            listaCoresOff = listaCoresOff.filter { it > 9 }.toMutableList()
                            if (listaCoresOff.size == 1 && listaCoresOff[0] == 0) {
                                val mesmaCorf =
                                    litOffsetExt.filter { listCoresExt[litOffsetExt.indexOf(it)] == intPreviewCor }
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
                            val listd =
                                litOffsetExt.filter { !listaCorteRamos.contains(it) } as MutableList<Int>
                            val lista1 = listd.filter { !listaCoresOff.contains(it) }
                            val listRA: MutableList<Int> = listaCoresOff
                            listRA.addAll(lista1)
                            var novalista: MutableList<Int> =
                                litOffsetExt.filter { !listRA.contains(it) } as MutableList<Int>
                            val listaCorteRamosNovo = playerLogic.updateLimparRamosinit(
                                listaAtual = litOffsetMove,
                                posL = novalista,
                                init = 0
                            )

                            for (iy in 0..<listaCoresOff.size) {
                                if (listaCoresOff.size < 3) break
                                litOffsetMove[listaCoresOff[iy]].vazio = false


                                novalista =
                                    litOffsetExt.filter { !listaCorteRamosNovo.contains(it) }
                                        .toMutableList()

                                val incluirRemovidosLeft =
                                    listRA.filter { !novalista.contains(it) }

                                novalista.addAll(incluirRemovidosLeft)
                                val rest: MutableList<Offset3> = mutableListOf()
                                for (iu in 0..<novalista.size) {
                                    val move: Offset3 = litOffsetMove[novalista[iu]]
                                    val o =
                                        Offset3(move.x + 0, move.y + 0, true, move.pos, move.linha)
                                    rest.add(o)
                                }
                                val listakkk = litOffsetMove.filter { !it.vazio }
                                val listakkk2 =
                                    listakkk.filter { !litOffsetExt.contains(it.pos) }
                                for (element in listakkk2) {
                                    litOffsetMove[element.pos].vazio = true
                                }
                                if (listakkk2.isNotEmpty()) {
                                    abilite.onUpdateMove(litOffsetMove)
                                }
                                novalista.sort()
                                abilite.onUpdateMoveReset(rest)

                                abilite.onUpdateRamos(novalista)
                            }

                            playerLogic.OnDim()

                            val newListCor = playerLogic.verificarCorPresente(listCoresExt)
                            intPreviewCor = intPreviewCor2

                            intPreviewCor2 = newListCor[(0..<newListCor.size).random()]
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

               Thread.sleep(60)

                subir = true

            }

        } else {

            playerLogic.onFim(tocar)
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
                    val b = BitmapFactory.decodeResource(res, R.drawable.blue)
                    CardFim(b, tela.context, modifier = modifier, "", fase, onclick = {

                    //    playerLogic.onMusica(false)
                     //   navController.navigate("mapa")

                        val initnew = fase.toInt()+1
                        navController.navigate("game/${initnew}")


                    })
                }

                //////////////////
            }
        }


    //////////////////////////////
}

@Composable
fun removendoDuplicados(litOffsetExt: MutableList<Int>): MutableList<Int> {
    val litOffsetExtAuxCop: MutableList<Int> = litOffsetExt.cop()
    val litOffsetExtAuxCopR: MutableList<Int> = mutableListOf()



    for (i in 1..<litOffsetExt.size) {

            if (litOffsetExt[i] == litOffsetExt[i - 1]) {
                litOffsetExtAuxCopR.add(litOffsetExt[i])
            }
        }


    if (litOffsetExtAuxCopR.size > 0) {
        for (j in 0..<litOffsetExtAuxCopR.size) {




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
    val litOffsetExtAuxCop: MutableList<Int> = mutableListOf()
    for (i in 0..<this.size) {
        litOffsetExtAuxCop.add(this[i] + 0)
    }
    return litOffsetExtAuxCop
}
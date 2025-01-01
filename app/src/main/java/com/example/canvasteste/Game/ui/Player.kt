package com.example.canvasteste.Game.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.TimeManager
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
    timeManager: TimeManager
) {
    val player = playerLogic.player.collectAsState()
    val ab = abilite.Abilite.collectAsState()
    val cr = cores.Cores.collectAsState()
    val cs = coresSeparacao.Cores.collectAsState()
    var gameouver: Boolean by remember { mutableStateOf(false) }
    var index: Boolean by remember { mutableStateOf(true) }

    val off = tela.getTamanhoTela()
    var ii: Float = (viewport.width / 2).toPx()
    var offsetX: Float by remember { mutableStateOf(ii) }
    var offsetX2: Float by remember { mutableStateOf(ii) }
    var offsetY by remember { mutableStateOf(off.y * 0.9) }
    val listCores =
        mutableListOf(R.drawable.red, R.drawable.blue, R.drawable.yaelow, R.drawable.pink)
    var intPreviewCor: Int by remember { mutableStateOf(listCores[(0..3).random()]) }
    var up: Dp by remember { mutableStateOf(1000.dp) }
    var rotation: Float by remember { mutableStateOf(-90f) }
    var offsetXi: Float by remember { mutableStateOf(0f) }
    var intPreview: Int by remember { mutableStateOf(0) }
    var intPreviewMarcado: Int by remember { mutableStateOf(0) }
    var positionEnd: Offset3 by remember { mutableStateOf(Offset3(0f, 0f, false, 0)) }
    var listPrev: List<Int> = player.value.posprev
    var subir: Boolean by remember { mutableStateOf(false) }
    var listaCoresOff: MutableList<Int> = cs.value.cores
    var listaCorteRamos: MutableList<Int> = ab.value.posRamo
    var litOffset = MutableList<Offset3>(179) { Offset3(0f, 1000f, false, 0) }
    var litOffsetExt: MutableList<Int> = ab.value.pos
    var listCoresExt: MutableList<Int> = cr.value.cores
    var qtd: Int = 30
    var unidadeT = (off.y * 0.9) / qtd
    var telaw = (off.x)
    var xi: Int = (((offsetX - ii) / qtd)).toInt()
    var i = qtd
    var go: Boolean by remember { mutableStateOf(false) }
    var xxiu = (ii + (xi * (qtd))).toInt()
    var xc = if (i == qtd) xxiu - ((30 / 2) / 2) else xxiu
    var dir: Float by remember { mutableStateOf(70f) }
    var xxPlay: Float by remember { mutableStateOf(xc.toFloat()) }
    var tocou: Boolean by remember { mutableStateOf(false) }
    var yyPlay: Float by remember { mutableStateOf((qtd * unidadeT).toFloat()) }
    var litOffset2 = mutableListOf<Offset>()
    var litORef = mutableListOf<Offset3>(
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1),
        Offset3(0f, 0f, true, -1)
    )
    var litOffsetExtFI = mutableListOf<Int>(10, 31, 52, 73, 94, 115, 136, 157)
    var litOffsetExtFI2 = mutableListOf<Int>(21, 42, 63, 84, 105, 126, 147, 168)
    var litOffsetExtS = mutableListOf<Int>()
    var lt = mutableListOf<Offset3>()
    var interi = 0
    var interiy = 1
    var tam = 36.dp.toPx()
    var tamMeio = 18.dp.toPx()
    if (subir && up > 0.dp) up -= 100.dp


    if(index) {
        for (i in 0..178) {
            var xp = interi * tam.toInt()
            var yp = interiy * tam.toInt()
            if (interiy % 2 == 0) {
                if (litOffsetExtFI2.contains(i)) {
                    interi = 0
                    interiy++
                    xp = tamMeio.toInt()
                    yp = interiy * tam.toInt()
                }
            } else {
                xp += tamMeio.toInt()
                if (litOffsetExtFI.contains(i)) {
                    interi = 0
                    interiy++
                    xp = 0
                    yp = interiy * tam.toInt()
                }
            }
            var vazio = true
            if (litOffsetExt.contains(i) || litOffsetExtS.contains(i)) {
                vazio = false
            }
            var offf: Offset3 = Offset3(
                xp.toFloat() + (15 * tela.densidade),
                yp + (15 * tela.densidade),
                vazio,
                i,
            )
            litOffset[i] = offf
            interi++
        }
        abilite.onUpdateMove(litOffset)
    }


    var litOffsetMove = if (ab.value.posMove.size > 1) ab.value.posMove else litOffset
    LaunchedEffect(key1 = Unit) {
        timeManager.deltaTime.collect { it ->
        }
    }
    Box(modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val coroutineScope = rememberCoroutineScope()
            val modifier = Modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = 0
                    )
                }
                .size(off.y.dp)
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
                                    go = true
                                    tocou = false
                                    interactionSource.emit(DragInteraction.Stop(this))
                                }
                            }
                        }
                    )
                }
            Surface(
                modifier = modifier,
                interactionSource = interactionSource,
                onClick = {},
                content = {},
                color = Color.Transparent
            )
        })
    if (!subir) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(-10.dp, 0.dp)
        ) {
            Button(
                onClick = { subir = !subir },
                modifier = Modifier
                    .offset(12.dp, 500.dp)
                    .size(110.dp)
            ) { Text("OK") }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(-10.dp, up)
    ) {
        for (i in 0..178) {
            var litOffsetMoveR = litOffsetMove[i]
            var id = R.drawable.car
            var image = R.drawable.car
            if (litOffsetExt.contains(i) || litOffsetExtS.contains(i)) {
                var corT: Int = listCoresExt[litOffsetExt.indexOf(i)]
                id = corT
            }
            if (i == intPreview && tocou) {
                image = R.drawable.tranp
            } else if (!litOffsetMoveR.vazio) {
                image = id
            }
            if (i <= 9) {
                image = R.drawable.car
            }
            if (i > 9 && listPrev.contains(i) && tocou) {
                image = R.drawable.tranp
            }
            if (listaCorteRamos.contains(i)) {
                var op = 20.dp.toPx().toInt()
                if (litOffsetMove[i].y >= 2000) {
                    listCoresExt.removeAt(litOffsetExt.indexOf(i))
                    listaCorteRamos.removeAt(listaCorteRamos.indexOf(i))
                    litOffsetExt.removeAt(litOffsetExt.indexOf(i))
                    cores.onUpdate(listCoresExt)
                    abilite.onUpdate(litOffsetExt)
                    abilite.onUpdateRamos(listaCorteRamos)
                } else {
                    litOffsetMove[i].y += op
                    offsetX2 -= 2
                }
                abilite.onUpdateMove(litOffsetMove)
            }
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
//            Text(i.toString(), modifier = modifier
//                .offset {
//                    IntOffset(
//                        x = litOffsetMoveR.x.toInt(),
//                        y = litOffsetMoveR.y.toInt()
//                    )
//                })
        }
        ///////////////////////////////////////////
        while (i > 0) {
            var xxi = (ii + (xi * (qtd - i))).toInt()
            if (xxi < 0) {
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
            if (xxi < 0) {
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
            if (xxi < 0) {
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
            var mostrar = true
            if (subir) {
                for (j in 0..litOffset.size - 1) {
                    if (!litOffset[j].vazio) {
                        var m1 = (litOffset[j].x - xc.toFloat()).pow(2)
                        var m2 = (litOffset[j].y - yc.toFloat()).pow(2)
                        var d: Float = sqrt((m1 + m2))
                        if (d < 30.dp.toPx()) {
                            mostrar = false
                            var litOffset3 = mutableListOf<Offset3>()
                            var lado = 0
                            try {
                                if (!litOffsetExtFI.contains(j + 1) && !litOffsetExtFI2.contains(j + 1)) {
                                    litOffset3.add(litOffset[j + 1])
                                }
                            } catch (e: Exception) {
                            }
                            try {
                                if (!litOffsetExtFI.contains(j) && !litOffsetExtFI2.contains(j)) {
                                    litOffset3.add(litOffset[j - 1])
                                }
                            } catch (e: Exception) {
                            }
                            try {
                                if (!litOffsetExtFI.contains(j)) {
                                    litOffset3.add(litOffset[j + 10])
                                }
                            } catch (e: Exception) {
                            }
                            try {
                                if (!litOffsetExtFI.contains(j + 11)) {
                                    litOffset3.add(litOffset[j + 11])
                                }
                            } catch (e: Exception) {
                            }
                            var litOffset4 = litOffset3.filter { it -> it.vazio == true }
                            if (litOffset4.size > 0) {
                                for (k in 0..litOffset4.size - 1) {
                                    var posArray = litOffset2.size - 2

                                    var m3 = (litOffset4[k].x - litOffset2[posArray].x).pow(2)
                                    var m4 = (litOffset4[k].y - litOffset2[posArray].y).pow(2)
                                    var d2: Float = sqrt((m3 + m4))
                                    if (d2 < 30.dp.toPx()) {
                                        lado = litOffset.indexOf(litOffset4[k])
                                        positionEnd = litOffset[lado]
                                        break
                                    }
                                }
                            }
                            intPreviewMarcado = j
                            if (j < 1200 && !go)
                                intPreview = lado

                            var mesmaCorf =
                                litOffsetExt.filter { it -> listCoresExt[litOffsetExt.indexOf(it)] == intPreviewCor }

                            if (lado != 0) {
                                listaCoresOff =
                                    playerLogic.updateLimparnit(litOffset, mesmaCorf, lado)
                            } else {
                                listaCoresOff = playerLogic.updateLimparnit(litOffset, mesmaCorf, j)
                            }
                            playerLogic.updatePrev(listaCoresOff)
                            break
                        }
                    }

                }
            }
            if (!mostrar) {
                break
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
            i--
        }
        if (go) {
            var lpp = mutableListOf(0)
            coresSeparacao.onUpdate(lpp)
            lt = player.value.posRebote
            dir =
                ((sqrt((((offsetX - xxPlay).dp.toPx()).pow(2)) + ((offsetY - yyPlay).pow(2)))) / 10).toFloat()
            if (lt[0].y < yyPlay) {
                yyPlay -= 50.dp.toPx()
                xxPlay = 500f
            }
            if (yyPlay <= lt[0].y) {
                go = false
                listCoresExt.add(intPreviewCor)
                litOffsetExt.add(intPreview)
                listaCoresOff = listaCoresOff.filter { it -> it > 9 }.toMutableList()
                if (listaCoresOff[0] == 0 && listaCoresOff.size == 1) {
                    var mesmaCorf =
                        litOffsetExt.filter { it -> listCoresExt[litOffsetExt.indexOf(it)] == intPreviewCor }
                    listaCoresOff = playerLogic.updateLimparnit(litOffset, mesmaCorf, intPreview)
                }
                for (iy in 0..listaCoresOff.size - 1) {
                    if (listaCoresOff.size < 3) break
                    litOffset[listaCoresOff[iy]].vazio = false
//                    try {
//
//
//                        listCoresExt.removeAt(litOffsetExt.indexOf(listaCoresOff[iy]))
//                        litOffsetExt.removeAt(litOffsetExt.indexOf(listaCoresOff[iy]))
//                        cores.onUpdate(listCoresExt)
//                        abilite.onUpdate(litOffsetExt)
//
//
//                    } catch (e: Exception) {
//                        Log.e("WAO", e.message.toString() + " " + e.stackTrace)
//                    }

                    listaCorteRamos = playerLogic.updateLimparRamosinit(
                        listaAtual = litOffset,
                        posL = litOffsetExt,
                        init = 0
                    )
                    var listd =
                        litOffsetExt.filter { it -> !listaCorteRamos.contains(it) } as MutableList<Int>
//                    for (iy in 0..listd.size - 1) {
//                        listCoresExt.removeAt(litOffsetExt.indexOf(listd[iy]))
//                        litOffsetExt.removeAt(litOffsetExt.indexOf(listd[iy]))
//                        cores.onUpdate(listCoresExt)
//                        abilite.onUpdate(litOffsetExt)
//                    }
                    // abilite.onUpdate(listd)
                    var lista1 = listd.filter { it -> !listaCoresOff.contains(it) }
                    var listRA: MutableList<Int> = listaCoresOff
                    listRA.addAll(lista1)
                    var novalista: MutableList<Int> =
                        litOffsetExt.filter { it -> !listRA.contains(it) } as MutableList<Int>
                    var listaCorteRamosNovo = playerLogic.updateLimparRamosinit(
                        listaAtual = litOffset,
                        posL = novalista,
                        init = 0
                    )
                    novalista = litOffsetExt.filter { it -> !listaCorteRamosNovo.contains(it) }
                        .toMutableList()
                    abilite.onUpdateRamos(novalista)
                }
                intPreviewCor = listCores[(0..3).random()]
                cores.onUpdate(listCoresExt)
                abilite.onUpdate(litOffsetExt)
                xxPlay = 0f
                yyPlay = ((qtd * unidadeT).toFloat())
                xxPlay = (telaw / 2) - 15.dp.toPx()
                yyPlay = ((qtd * unidadeT).toFloat())
            }
        }
    }
}









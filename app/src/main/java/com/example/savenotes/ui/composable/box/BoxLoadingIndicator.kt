package com.example.savenotes.ui.composable.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.savenotes.ui.util.noRippleClickable

@Composable
//'isLoading' determina si si el indicador de carga debe mostrarse o no
//'customHeight: Dp? = null' es un parámetro opcional que permite especificar una altura
// personalizada para el Box, si es null, como en este caso, el Box ocupará every el tamaño
// disponible
//'isVisible' es un parámetro que controla si 'CircularProgressIndicator' dentro del Box
// es visible, por lo general se coloca en 'true'
fun BoxLoadingIndicator(isLoading: Boolean, customHeight: Dp? = null, isVisible: Boolean = true) {

    val modifier =
        if (customHeight == null) Modifier.fillMaxSize()
        else Modifier.fillMaxWidth().height(customHeight)

    if (isLoading) {
        Box(
            //'.noRippleClickable {}' hace que el área intercepte los eventos de clic para
            // que no pasen al contenido detrás de ella, pero sin mostrar el efecto visual de
            // ripple.
            modifier = modifier.background(Color.Transparent).noRippleClickable {},
            contentAlignment = Alignment.Center,
        ) {
            //'CircularProgressIndicator' muestra una animación de progreso circular.
            //'MaterialTheme.colorScheme.primary' establece el color primario definido en el
            // Material Design de la aplicación
            if (isVisible) CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

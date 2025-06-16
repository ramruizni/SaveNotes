package com.example.savenotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.savenotes.feature.login.LoginScreen
import com.example.savenotes.feature.main.MainScreen
import com.example.savenotes.navigation.login.LoginRoute
import com.example.savenotes.navigation.main.MainRoute

//Este composable es el que se va a encargar de orquestar la navegación, y que va a conocer las
// pantallas de nuestra app, y que se va a encargar de gestionar el paso entre ellas de la
// manera correcta
@Composable
fun NavigationHost() {
    //'rememberNavController()' rememberNavController() Crea y recuerda (remember) una
    // instancia de NavController. 'NavController' lleva una registro y Orquesta las
    // operaciones de navegación (como navigate() y popBackStack()).
    val navController = rememberNavController()
    //'navController' se tiene que propagar entre todas las pantallas, ya que se va a
    // encargar cúal es el estado de navegación entre ellas, para así podemos desplazar
    // entre cada una

    //'NavHost' actúa como un contenedor que nos muestra el destino actual del grafo
    // de navegación. Si navegamos a un nuevo destino, el NavHost se recompone para
    // mostrar el Composable asociado con ese nuevo destino.
    //Además el 'NavHost' va a cononcer las pantallas y cómo navegar entre ellas.
    NavHost(
        //El NavHost usa este navController para determinar qué Composable mostrar
        // y para responder a las acciones de navegación
        navController = navController,
        //'startDestination' cuál de tus destinos de navegación debe mostrarse inicialmente
        // cuando el NavHost se compone por primera vez
        startDestination = LoginRoute
    ) {
        //Los composables dentro de NavHost representan los diferentes destinos a los que el
        // usuario puede navegar
        //'<LoginRoute>' es el tipo de ruta para este destino
        composable<LoginRoute> {
            // Cuando el navController navegue a la ruta 'LoginRoute', el NavHost mostrará
            // el contenido de LoginScreen
            LoginScreen(
                // Al pasarle navController a LoginScreen, permitimos que LoginScreen pueda
                //desencadenar acciones de navegación
                navController = navController
            )
        }

        composable<MainRoute> {
            MainScreen()
        }
    }
}
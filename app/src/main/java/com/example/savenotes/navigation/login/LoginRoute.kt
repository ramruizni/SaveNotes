package com.example.savenotes.navigation.login

import kotlinx.serialization.Serializable

// @Serializable permite que LoginRoute sea pasado como argumento de navegación de una
// manera segura y estructurada
//object se utiliza para declarar una clase que tiene una única instancia (un singleton)
//Cuando se aplica a un object, la palabra clave data proporciona automáticamente
// implementaciones de métodos estándar que son útiles, principalmente toString().
@Serializable data object LoginRoute


// En resumen,  '@Serializable data object LoginRoute' define un destino de navegación
// llamado 'LoginRoute'.
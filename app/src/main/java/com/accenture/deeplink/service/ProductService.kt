package com.accenture.deeplink.service

import com.accenture.deeplink.model.Product

object ProductService {
    val list: MutableList<Product> = mutableListOf()

    fun getSize() = list.size
    fun getElement(position: Int) = list[position]
    fun initList() {
        list.addAll(
            listOf(
                Product(
                    id = "fogr5boufefiow",
                    name = "Libro",
                    stock = 3,
                    photo = android.R.drawable.ic_menu_recent_history,
                    selected = false,
                    metaImage = "https://m.media-amazon.com/images/I/91pelQf-w7S._SL1500_.jpg"
                ),
                Product(
                    id = "ijefgoeurwwrvo",
                    name = "Camara",
                    stock = 3,
                    photo = android.R.drawable.ic_menu_camera,
                    selected = false,
                    metaImage = "https://m.media-amazon.com/images/I/914hFeTU2-L._SX679_.jpg"
                ),
                Product(
                    id = "fogr5dnif0c2ej",
                    name = "Agenda",
                    stock = 3,
                    photo = android.R.drawable.ic_menu_agenda,
                    selected = false,
                    metaImage = "https://i0.wp.com/www.craftart.cl/wp-content/uploads/2022/09/mkp.png?fit=1200%2C805&ssl=1"
                ),
                Product(
                    id = "fogr5boerf4i24",
                    name = "Telefono",
                    stock = 3,
                    photo = android.R.drawable.ic_menu_call,
                    selected = false,
                    metaImage = "https://fdn.gsmarena.com/imgroot/static/headers/makers/apple-2023-1.jpg"
                ),
                Product(
                    "f4rjfno4fervo9",
                    "Calendario 2023",
                    3,
                    android.R.drawable.ic_menu_my_calendar,
                    selected = false,
                    metaImage = "https://img.freepik.com/vector-gratis/colorida-plantilla-calendario-2023-estilo-imprimible_1017-40661.jpg?w=2000"
                ),
                Product(
                    id = "fodn20c4949fr0",
                    name = "Pintura",
                    stock = 3,
                    photo = android.R.drawable.ic_menu_gallery,
                    selected = false,
                    metaImage = "https://hips.hearstapps.com/hmg-prod/images/la-noche-estrellada1-1586872678-6438fc97a96e4.jpg?crop=1xw:0.703125xh;center,top"
                )
            )
        )
    }
}

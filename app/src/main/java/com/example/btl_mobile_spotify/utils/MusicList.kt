package com.example.btl_mobile_spotify.utils

import com.example.btl_mobile_spotify.data.models.local.Music
import java.util.*

val sampleMusicList = listOf(
    Music(
        id = UUID.randomUUID().toString(),
        title = "Sunflower - Spider-Man: Into the Spider-Verse",
        artists = listOf("Post Malone", "Swae Lee"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e02e2e352d89826aef6dbd5ff8f",
        musicUrl = "",
        duration = 158000
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "I'm Ready",
        artists = listOf("Jaden"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e026094c28a79dc47837fd269d9",
        musicUrl = "",
        duration = 189000
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Rap God",
        artists = listOf("Eminem"),
        imageUrl = "https://i.scdn.co/image/ab67616d00004851643e6ecebab400d52574e4b2",
        musicUrl = "",
        duration = 363000
    ),

    // data set 2
    Music(
        id = UUID.randomUUID().toString(),
        title = "Ranjha - Shershaah",
        artists = listOf("Jasleen Royal", "B Praak"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
        musicUrl = "",
        duration = (3 * 60 + 48) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Tune Jo Na Kaha",
        artists = listOf("Pritam", "Mohit Chauhan", "Sandeep Shrivastava"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e02d567bc615d8d891d112c8a35",
        musicUrl = "",
        duration = (5 * 60 + 10) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Same Beef",
        artists = listOf("Bohemia", "Siddhu Moosewala"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e02d6181999e3c1c1bb525f9989",
        musicUrl = "",
        duration = (4 * 60 + 22) * 1000L
    ),

    // data set 3
    Music(
        id = UUID.randomUUID().toString(),
        title = "Cắt đôi nỗi sầu",
        artists = listOf("Tăng Duy Tân"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20T%C4%82NG%20DUY%20T%C3%82N%20%20C%E1%BA%AET%20%C4%90%C3%94I%20N%E1%BB%96I%20S%E1%BA%A6U%20ft%20DRUM7%20%20OFFICIAL%20MUSIC%20VIDEO.mp3?alt=media&token=3838ce30-c810-4064-b8a2-23f4a95c2c4a&_gl=1*q480z8*_ga*MTM2NTg1OTE5Ny4xNjk2MjU1NjMx*_ga_CW55HF8NVT*MTY5NzUyNTIzNS4xMi4xLjE2OTc1MzAyNjkuNDAuMC4w",
        duration = (3 * 60 + 23) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Viva la Vida",
        artists = listOf("Shalom Margaret"),
        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Full%20Lyrics%20%20Vietsub%20Viva%20la%20Vida%20%20Shalom%20Margaret%20cover.mp3?alt=media&token=bbd27031-2f2f-438f-8986-d93a7a640b74",
        duration = 168000
    ),

)

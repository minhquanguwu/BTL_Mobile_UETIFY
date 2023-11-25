package com.example.btl_mobile_spotify.utils

import com.example.btl_mobile_spotify.data.models.local.Music
import java.util.*

val sampleMusicList = listOf(
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Sunflower - Spider-Man: Into the Spider-Verse",
//        artists = listOf("Post Malone", "Swae Lee"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e02e2e352d89826aef6dbd5ff8f",
//        musicUrl = "",
//        duration = 158000
//    ),
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "I'm Ready",
//        artists = listOf("Jaden"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e026094c28a79dc47837fd269d9",
//        musicUrl = "",
//        duration = 189000
//    ),
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Rap God",
//        artists = listOf("Eminem"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00004851643e6ecebab400d52574e4b2",
//        musicUrl = "",
//        duration = 363000
//    ),
//
//    // data set 2
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Ranjha - Shershaah",
//        artists = listOf("Jasleen Royal", "B Praak"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
//        musicUrl = "",
//        duration = (3 * 60 + 48) * 1000L
//    ),
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Tune Jo Na Kaha",
//        artists = listOf("Pritam", "Mohit Chauhan", "Sandeep Shrivastava"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e02d567bc615d8d891d112c8a35",
//        musicUrl = "",
//        duration = (5 * 60 + 10) * 1000L
//    ),
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Same Beef",
//        artists = listOf("Bohemia", "Siddhu Moosewala"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e02d6181999e3c1c1bb525f9989",
//        musicUrl = "",
//        duration = (4 * 60 + 22) * 1000L
//    ),
//
//    // data set 3
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Cắt đôi nỗi sầu",
//        artists = listOf("Tăng Duy Tân"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
//        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20T%C4%82NG%20DUY%20T%C3%82N%20%20C%E1%BA%AET%20%C4%90%C3%94I%20N%E1%BB%96I%20S%E1%BA%A6U%20ft%20DRUM7%20%20OFFICIAL%20MUSIC%20VIDEO.mp3?alt=media&token=3838ce30-c810-4064-b8a2-23f4a95c2c4a&_gl=1*q480z8*_ga*MTM2NTg1OTE5Ny4xNjk2MjU1NjMx*_ga_CW55HF8NVT*MTY5NzUyNTIzNS4xMi4xLjE2OTc1MzAyNjkuNDAuMC4w",
//        duration = (3 * 60 + 23) * 1000L
//    ),
//    Music(
//        id = UUID.randomUUID().toString(),
//        title = "Viva la Vida",
//        artists = listOf("Shalom Margaret"),
//        imageUrl = "https://i.scdn.co/image/ab67616d00001e0209426d9ae9d8d981735ebc5e",
//        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Full%20Lyrics%20%20Vietsub%20Viva%20la%20Vida%20%20Shalom%20Margaret%20cover.mp3?alt=media&token=bbd27031-2f2f-438f-8986-d93a7a640b74",
//        duration = 168000
//    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Viva la Vida",
        artists = listOf("Coldplay"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/viva%20la%20vid.jpg?alt=media&token=2f8f6caf-8682-4ae6-9760-a44cc8f322e8",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Coldplay%20%20Viva%20La%20Vida%20Official%20Video.mp3?alt=media&token=411ba064-e2b5-4677-a37f-141d09a0106f",
        duration = (4 * 60 + 2) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Bille Jean",
        artists = listOf("Michael Jackson"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/billie%20jean.jpg?alt=media&token=c537eea0-6f7f-4b75-a627-be188ce8e639",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Billie%20Jean.mp3?alt=media&token=11b23c3a-802e-4679-a098-22403fab3049",
        duration = (4 * 60 + 54) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Smooth Criminal",
        artists = listOf("Michael Jackson"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/smooth%20criminal.jpg?alt=media&token=bde5544f-7f01-446a-9f9c-ae8f271b4130",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Michael%20Jackson%20%20Smooth%20Criminal%20Official%20Video%20%20Shortened%20Version.mp3?alt=media&token=f54f4582-ee12-4996-814e-a031982d65f6",
        duration = (4 * 60 + 10) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Tìm Lại",
        artists = listOf("Microwave"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/t%C3%ACm%20l%E1%BA%A1i.jpg?alt=media&token=ad2196a5-562f-4e12-92d5-be71a1f686a5",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20MicroWave%20%20%20Tim%20Lai.mp3?alt=media&token=0cf9f96f-de90-42fc-89df-d403f4794b32",
        duration = (3 * 60 + 45) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Hotel California",
        artists = listOf("Eagles"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/hotel%20california.jpg?alt=media&token=6bb05476-eac5-48e5-b558-8951fb74d26d",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Eagles%20%20Hotel%20California%20Lossless%20Audio.mp3?alt=media&token=34efc22e-37bd-4b38-87d8-666cd473c62c",
        duration = (6 * 60 + 30) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Mary on a Cross",
        artists = listOf("Ghost"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/mary%20on%20a%20cross.jpg?alt=media&token=0577e2ac-e15f-4b58-953c-b5434b635c8e",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Ghost%20%20Mary%20On%20A%20Cross%20Official%20Audio.mp3?alt=media&token=f0e78882-5ff9-430b-924a-b21466fd6f86",
        duration = (4 * 60 + 4) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Can Can",
        artists = listOf("Offenbach"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/offenbach.jpg?alt=media&token=21472ab8-3c48-4630-875c-dc9156be24ae",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Orpheus%20in%20the%20Underworld%20Can%20Can.mp3?alt=media&token=0e94c317-560a-4379-af69-7c1501a67f15",
        duration = (2 * 60 + 11) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Around the World (La La La La La)",
        artists = listOf("A Touch of Class"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/download.jpg?alt=media&token=20eec48a-c597-46c4-90d7-cab09c00b7ca",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Around%20the%20World%20La%20La%20La%20La%20La%20Radio%20Version.mp3?alt=media&token=79f4aafc-9e3c-4743-b033-195e7fe68af9",
        duration = (3 * 60 + 34) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Dancing in the Moonlight",
        artists = listOf("Toploader"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/dancing%20in%20the%20moonlight.jpg?alt=media&token=69421173-9579-424a-b56f-fc67890f5040",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Toploader%20%20Dancing%20in%20the%20Moonlight%20Official%20Video.mp3?alt=media&token=4c5c7cb3-7f89-4758-9313-85906a8cabc4",
        duration = (3 * 60 + 45) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Nối Vòng Tay Lớn",
        artists = listOf("Various Artists"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/noi%20vong%20tay%20lon.jpg?alt=media&token=8e7f78b8-4753-48ab-bfe0-5c67caa02ef3",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Noi%20Vong%20Tay%20Lon%20by%20Rock%20Viet%20Bands%20official%20MV.mp3?alt=media&token=edac0ae9-4fca-4b85-b055-fc3353c1511a",
        duration = (5 * 60 + 20) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Wake Me Up",
        artists = listOf("Avicii"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/wake%20me%20up.jpg?alt=media&token=88f7ea2c-5c0e-403e-a00b-e38db7dd908b",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Avicii%20%20Wake%20Me%20Up%20Official%20Video.mp3?alt=media&token=8566e201-f26d-4a48-95f7-15f68f2aa9b1",
        duration = (4 * 60 + 32) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Fly Me To The Moon",
        artists = listOf("Frank Sinatra"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/fly%20me%20to%20the%20moon.jpg?alt=media&token=7e978451-ea68-4a7f-b4cc-34ef6fdd550b",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Fly%20Me%20To%20The%20Moon%202008%20Remastered.mp3?alt=media&token=0fd9b7b7-dc2e-4f20-968e-1c510ff957da",
        duration = (2 * 60 + 27) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Seven Nation Army",
        artists = listOf("The White Stripes"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/seven%20nation%20army.jpg?alt=media&token=eee4470d-70d4-44cf-829b-5f5b58d2506b",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20The%20White%20Stripes%20%20Seven%20Nation%20Army%20Official%20Music%20Video.mp3?alt=media&token=bf51a894-5f56-4a0d-8718-d2a44920d97f",
        duration = (3 * 60 + 59) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Take Me Home, Country Roads",
        artists = listOf("John Denver"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/country%20road%20take%20me%20home.jpg?alt=media&token=aad5cec8-e442-4b5c-a93b-f06344add48d",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20John%20Denver%20%20Take%20Me%20Home%20Country%20Roads%20Official%20Audio.mp3?alt=media&token=26be593d-b6fa-4013-b583-56041c9f1476",
        duration = (3 * 60 + 18) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "(I Just) Died In Your Arms",
        artists = listOf("Cutting Crew"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/i%20just%20died%20in%20your%20arms%20tonight.jpg?alt=media&token=5daeef51-3d34-48bd-9cc3-da79b620568b",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Cutting%20Crew%20%20I%20Just%20Died%20In%20Your%20Arms%20Official%20Music%20Video.mp3?alt=media&token=1f8591b3-48ee-471d-b7ff-5de140a11aab",
        duration = (4 * 60 + 32) * 1000L
    ),

    Music(
        id = UUID.randomUUID().toString(),
        title = "Lonely Boy",
        artists = listOf("The Black Keys"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/lonely%20boy.jpg?alt=media&token=43c53f47-51eb-4779-9442-379a061aee6d",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20The%20Black%20Keys%20%20Lonely%20Boy%20Official%20Music%20Video.mp3?alt=media&token=5ac505b3-0783-4185-be1d-0281b53e3026",
        duration = (3 * 60 + 15) * 1000L
    ),
    Music(
        id = UUID.randomUUID().toString(),
        title = "Don't Stop Believin'",
        artists = listOf("Journey"),
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/don't%20stop%20believin'.jpg?alt=media&token=68913482-4636-43ce-a9ae-b198a4cbade3",
        musicUrl = "https://firebasestorage.googleapis.com/v0/b/spotify-1bf9e.appspot.com/o/y2mate.com%20-%20Journey%20%20Dont%20Stop%20Believin%20Official%20Audio.mp3?alt=media&token=1312e720-23ae-44e2-ab7d-8ba38437dd1d",
        duration = (4 * 60 + 10) * 1000L
    ),


    )

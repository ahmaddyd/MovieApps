package com.jetpack.movies.utils

import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.data.remote.response.ResultMovie
import com.jetpack.movies.data.remote.response.ResultTv

object DataDummy {
    fun generateDummyMovies(): ArrayList<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()
        movies.add(
            MoviesEntity(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                567189,
                "Tom Clancy's Without Remorse",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "2021-04-29",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                578701,
                "Those Who Wish Me Dead",
                "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                "2021-05-05",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                632357,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                615457,
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                813258,
                "Monster Pets: A Hotel Transylvania Short",
                "Drac tries out some new monster pets to help occupy Tinkles for playtime.",
                "2021-04-02",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dkokENeY5Ka30BFgWAqk14mbnGs.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "2020-10-16",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                811367,
                "22 vs. Earth",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "2021-04-30",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/32vLDKSzcCMh55zqqaSqqUA8naz.jpg"
            )
        )

        movies.add(
            MoviesEntity(
                726684,
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "2021-04-04",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/msI5a9TPnepx47JUb2vl88hb80R.jpg.jpg"
            )
        )

        return movies
    }

    fun generateDummyTvs(): ArrayList<TvShowsEntity> {
        val tvs = ArrayList<TvShowsEntity>()
        tvs.add(
            TvShowsEntity(
                120168,
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "2021-03-24",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives.",
                "2017-09-25",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                95557,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                1416,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                63174,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-01-25",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                86831,
                "Love, Death & Robots",
                "Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.",
                "2019-03-15",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/asDqvkE66EegtKJJXIRhBJPxscr.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                69050,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"
            )
        )

        tvs.add(
            TvShowsEntity(
                93484,
                "Jupiter's Legacy",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "2021-05-07",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9yxep7oJdkj3Pla9TD9gKflRApY.jpg"
            )
        )

        return tvs
    }

    fun generateRemoteDummyMovies(): List<ResultMovie> {
        val movies = ArrayList<ResultMovie>()
        movies.add(
            ResultMovie(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
            )
        )

        movies.add(
            ResultMovie(
                567189,
                "Tom Clancy's Without Remorse",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "2021-04-29",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"
            )
        )

        movies.add(
            ResultMovie(
                578701,
                "Those Who Wish Me Dead",
                "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                "2021-05-05",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg"
            )
        )

        movies.add(
            ResultMovie(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"
            )
        )

        movies.add(
            ResultMovie(
                632357,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg"
            )
        )

        movies.add(
            ResultMovie(
                615457,
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg"
            )
        )

        movies.add(
            ResultMovie(
                813258,
                "Monster Pets: A Hotel Transylvania Short",
                "Drac tries out some new monster pets to help occupy Tinkles for playtime.",
                "2021-04-02",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dkokENeY5Ka30BFgWAqk14mbnGs.jpg"
            )
        )

        movies.add(
            ResultMovie(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "2020-10-16",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"
            )
        )

        movies.add(
            ResultMovie(
                811367,
                "22 vs. Earth",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "2021-04-30",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/32vLDKSzcCMh55zqqaSqqUA8naz.jpg"
            )
        )

        movies.add(
            ResultMovie(
                726684,
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "2021-04-04",
                8.8,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/msI5a9TPnepx47JUb2vl88hb80R.jpg.jpg"
            )
        )

        return movies
    }

    fun generateRemoteDummyTvs(): List<ResultTv> {
        val tvs = ArrayList<ResultTv>()
        tvs.add(
            ResultTv(
                120168,
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "2021-03-24",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg"
            )
        )

        tvs.add(
            ResultTv(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )

        tvs.add(
            ResultTv(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
            )
        )

        tvs.add(
            ResultTv(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives.",
                "2017-09-25",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )

        tvs.add(
            ResultTv(
                95557,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg"
            )
        )

        tvs.add(
            ResultTv(
                1416,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )

        tvs.add(
            ResultTv(
                63174,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-01-25",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
            )
        )

        tvs.add(
            ResultTv(
                86831,
                "Love, Death & Robots",
                "Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.",
                "2019-03-15",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/asDqvkE66EegtKJJXIRhBJPxscr.jpg"
            )
        )

        tvs.add(
            ResultTv(
                69050,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"
            )
        )

        tvs.add(
            ResultTv(
                93484,
                "Jupiter's Legacy",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "2021-05-07",
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9yxep7oJdkj3Pla9TD9gKflRApY.jpg"
            )
        )

        return tvs
    }

    fun generateMovieById(moviesId: Int): MoviesEntity {
        return MoviesEntity(
            460465,
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "2021-04-07",
            8.8,
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
        )
    }

    fun generateTvById(tvId: Int): TvShowsEntity {
        return TvShowsEntity(
            120168,
            "Who Killed Sara?",
            "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
            "2021-03-24",
            7.2,
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg"
        )
    }
}
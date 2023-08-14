package com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.mukhaled.englishpremierleagueapp.R
import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.presentation.theme.Pink80

@Composable
fun TodayMatchScreen(state: UiState) {
    val grouped = state.premierLeague.matches.groupBy { it.date }
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .size(142.dp, 60.dp)
                .padding(top = 12.dp, start = 24.dp, bottom = 8.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        PremierLeagueList(grouped)
    }
}

@Composable
fun PremierLeagueList(grouped: Map<String, List<ItemData>>) {
    LazyColumn {
        grouped.forEach { (initial, contactsForInitial) ->
            item {
                CharacterHeader(contactsForInitial[0].day, initial)
            }
            items(contactsForInitial.size) { itemIndex ->
                Item(contactsForInitial[itemIndex])
            }
        }
    }
}

@Composable
fun CharacterHeader(day: String, date: String) {
    Row(
        modifier = Modifier.padding(start = 28.dp, top = 24.dp, bottom = 12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = day, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = date, fontSize = 10.sp, color = Color.LightGray)
    }
}

@Composable
fun Item(data: ItemData) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.charleston_green)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 28.dp, bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = data.teamHome,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Pink80
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .background(
                            color = when (data.matchState) {
                                "LIVE NOW" -> Color.Red
                                "CANCELED" -> Color("#825B64".toColorInt())
                                "SCHEDULED" -> Color("#BD687C".toColorInt())
                                else -> {
                                    Color("#C79CA6".toColorInt())
                                }
                            },
                            shape = RoundedCornerShape(bottomEnd = 4.dp, bottomStart = 4.dp)
                        )
                        .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                    text = data.matchState,
                    fontSize = 8.sp,
                )
                Text(modifier = Modifier.padding(4.dp), text = data.day, fontSize = 10.sp)
                Text(
                    text = "${data.homeScore} - ${data.awayScore}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                val started = if (data.matchState == "FINISHED") "Started at" else "Start at"
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "$started ${data.time}",
                    fontSize = 10.sp
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = data.teamAway,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Pink80
            )
        }
        Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp))
        Box(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Premier League",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Text(
                text = "week 1",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize()
                    .align(Alignment.BottomEnd),
                fontSize = 10.sp
            )
        }
    }
}


@Preview
@Composable
fun ItemPrev() {
    Item(
        ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Sunday",
            date = "12 agust 2023"
        )
    )
}

@Preview
@Composable
fun PremierLeaguePrev() {
    val list = listOf<ItemData>(
        ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Sunday",
            date = "12 agust 2023"
        ), ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Sunday",
            date = "12 agust 2023"
        ), ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Sunday",
            date = "12 agust 2023"
        ), ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Monday",
            date = "13 agust 2023"
        ), ItemData(
            id = 1,
            teamHome = "Manchester Utd",
            teamAway = "Arsnel",
            time = "17:00 PM",
            matchState = "live now",
            awayScore = "0",
            homeScore = "2",
            day = "Monday",
            date = "13 agust 2023"
        )
    )
    val grouped = list.groupBy { it.date }
    PremierLeagueList(grouped)
}
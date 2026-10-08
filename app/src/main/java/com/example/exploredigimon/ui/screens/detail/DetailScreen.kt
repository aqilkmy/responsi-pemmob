package com.example.exploredigimon.ui.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.exploredigimon.data.model.DigimonDetailResponse
import com.example.exploredigimon.ui.common.UiState
import com.example.exploredigimon.ui.theme.BorderSubtle
import com.example.exploredigimon.ui.theme.CardSurface
import com.example.exploredigimon.ui.theme.ChampionTeal
import com.example.exploredigimon.ui.theme.ChampionTealContainer
import com.example.exploredigimon.ui.theme.DataBlue
import com.example.exploredigimon.ui.theme.DataBlueContainer
import com.example.exploredigimon.ui.theme.DataBlueText
import com.example.exploredigimon.ui.theme.DigitalCyan
import com.example.exploredigimon.ui.theme.DigitalCyanContainer
import com.example.exploredigimon.ui.theme.DigiviceOrange
import com.example.exploredigimon.ui.theme.DigiviceOrangeContainer
import com.example.exploredigimon.ui.theme.DigiviceOrangeDark
import com.example.exploredigimon.ui.theme.FreeAmber
import com.example.exploredigimon.ui.theme.FreeAmberContainer
import com.example.exploredigimon.ui.theme.FreeAmberText
import com.example.exploredigimon.ui.theme.TextBody
import com.example.exploredigimon.ui.theme.TextMuted
import com.example.exploredigimon.ui.theme.TextTitle
import com.example.exploredigimon.ui.theme.UltimateMagenta
import com.example.exploredigimon.ui.theme.UltimateMagentaContainer
import com.example.exploredigimon.ui.theme.VaccineGreen
import com.example.exploredigimon.ui.theme.VaccineGreenContainer
import com.example.exploredigimon.ui.theme.VaccineGreenText
import com.example.exploredigimon.ui.theme.VirusPurple
import com.example.exploredigimon.ui.theme.VirusPurpleContainer
import com.example.exploredigimon.ui.theme.VirusPurpleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    digimonId: Int,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(digimonId) {
        viewModel.fetchDetail(digimonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Digimon",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextTitle
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = DigiviceOrange
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CardSurface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    DetailLoadingView()
                }

                is UiState.Error -> {
                    DetailErrorView(
                        message = state.message,
                        onRetry = { viewModel.fetchDetail(digimonId) }
                    )
                }

                is UiState.Success -> {
                    DetailContentView(digimon = state.data)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailContentView(digimon: DigimonDetailResponse) {
    val scrollState = rememberScrollState()
    val imageUrl = digimon.images.firstOrNull()?.href

    val englishDescription = digimon.descriptions.firstOrNull {
        it.language.equals("en_us", ignoreCase = true)
    }?.description ?: digimon.descriptions.firstOrNull()?.description

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardSurface
            ),
            border = BorderStroke(1.dp, BorderSubtle),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = DigiviceOrangeContainer
                    ) {
                        Text(
                            text = "#${digimon.id}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = DigiviceOrangeDark,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    if (digimon.xAntibody) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = UltimateMagentaContainer
                        ) {
                            Text(
                                text = "X-Antibody",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = UltimateMagenta,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .size(170.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    DigitalCyanContainer,
                                    DigiviceOrangeContainer.copy(alpha = 0.6f)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = digimon.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = digimon.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextTitle,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardSurface
            ),
            border = BorderStroke(1.dp, BorderSubtle),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Informasi Karakter",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextTitle
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val levelText = digimon.levels.firstOrNull()?.level?.ifBlank { null } ?: "-"
                    ColorfulInfoBox(
                        title = "Level",
                        value = levelText,
                        icon = Icons.Default.Star,
                        accentColor = FreeAmber,
                        containerColor = FreeAmberContainer,
                        textColor = FreeAmberText,
                        modifier = Modifier.weight(1f)
                    )

                    val attributeText = digimon.attributes.firstOrNull()?.attribute?.ifBlank { null } ?: "-"
                    val (attrAccent, attrContainer, attrText) = when (attributeText.lowercase()) {
                        "vaccine" -> Triple(VaccineGreen, VaccineGreenContainer, VaccineGreenText)
                        "data" -> Triple(DataBlue, DataBlueContainer, DataBlueText)
                        "virus" -> Triple(VirusPurple, VirusPurpleContainer, VirusPurpleText)
                        else -> Triple(FreeAmber, FreeAmberContainer, FreeAmberText)
                    }
                    ColorfulInfoBox(
                        title = "Attribute",
                        value = attributeText,
                        icon = Icons.Default.Shield,
                        accentColor = attrAccent,
                        containerColor = attrContainer,
                        textColor = attrText,
                        modifier = Modifier.weight(1f)
                    )

                    val typeText = digimon.types.firstOrNull()?.type?.ifBlank { null } ?: "-"
                    ColorfulInfoBox(
                        title = "Type",
                        value = typeText,
                        icon = Icons.Default.Widgets,
                        accentColor = ChampionTeal,
                        containerColor = ChampionTealContainer,
                        textColor = ChampionTeal,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        if (digimon.levels.size > 1 || digimon.attributes.size > 1 || digimon.types.size > 1) {
            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardSurface
                ),
                border = BorderStroke(1.dp, BorderSubtle),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Klasifikasi Lengkap",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextTitle
                    )

                    if (digimon.levels.size > 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Level:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = FreeAmberText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            digimon.levels.forEach {
                                ColorfulChip(text = it.level, bg = FreeAmberContainer, fg = FreeAmberText)
                            }
                        }
                    }

                    if (digimon.attributes.size > 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Attribute:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = DigitalCyan
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            digimon.attributes.forEach {
                                ColorfulChip(text = it.attribute, bg = DigitalCyanContainer, fg = DigitalCyan)
                            }
                        }
                    }

                    if (digimon.types.size > 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Type:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = ChampionTeal
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            digimon.types.forEach {
                                ColorfulChip(text = it.type, bg = ChampionTealContainer, fg = ChampionTeal)
                            }
                        }
                    }
                }
            }
        }

        if (!englishDescription.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardSurface
                ),
                border = BorderStroke(1.dp, BorderSubtle),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = DigiviceOrange,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Deskripsi",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextTitle
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = englishDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextBody,
                        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                    )
                }
            }
        }

        if (digimon.skills.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardSurface
                ),
                border = BorderStroke(1.dp, BorderSubtle),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Keahlian & Jurus",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextTitle
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    digimon.skills.take(5).forEach { skillItem ->
                        SkillCardItem(skill = skillItem.skill, desc = skillItem.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ColorfulInfoBox(
    title: String,
    value: String,
    icon: ImageVector,
    accentColor: Color,
    containerColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = containerColor
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ColorfulChip(text: String, bg: Color, fg: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = bg
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = fg,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun SkillCardItem(skill: String, desc: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(DigitalCyanContainer.copy(alpha = 0.4f))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = DigiviceOrange,
                modifier = Modifier.size(8.dp)
            ) {}
            Text(
                text = skill,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextTitle
            )
        }
        if (!desc.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = TextBody
            )
        }
    }
}

@Composable
fun DetailLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = DigiviceOrange,
                strokeWidth = 3.dp,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Menganalisis data Digimon...",
                style = MaterialTheme.typography.bodyMedium,
                color = TextBody
            )
        }
    }
}

@Composable
fun DetailErrorView(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardSurface
            ),
            border = BorderStroke(1.dp, BorderSubtle),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = DigiviceOrange,
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Gagal Mengambil Detail",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextTitle
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    onClick = onRetry,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DigiviceOrange
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Coba Lagi")
                }
            }
        }
    }
}

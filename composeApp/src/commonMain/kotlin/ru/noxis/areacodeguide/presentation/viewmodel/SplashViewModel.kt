package ru.noxis.areacodeguide.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.noxis.areacodeguide.core.domain.onError
import ru.noxis.areacodeguide.core.domain.onSuccess
import ru.noxis.areacodeguide.core.presentation.toUiText
import ru.noxis.areacodeguide.domain.Region
import ru.noxis.areacodeguide.domain.RegionsRepository
import ru.noxis.areacodeguide.presentation.state.SplashState

class SplashViewModel(
    private val regionsRepository: RegionsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.onStart {
        initDb()
    }.stateIn(viewModelScope, SharingStarted.Lazily, _state.value)


    private fun initDb() {
        regionsRepository.getRegions()
            .onStart {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
            .onEach { regions ->
                if (regions.isEmpty()) {
                    regionsRepository.initDb(dataRegions)
                        .onSuccess {
                            delay(1500)
                            _state.update {
                                it.copy(isLoading = false, error = null)
                            }
                        }
                        .onError {
                            _state.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = it.toUiText()
                                )
                            }
                        }
                }

                delay(1500)
                _state.update {
                    it.copy(isLoading = false, error = null)
                }
            }.launchIn(viewModelScope)
    }

}

private val dataRegions = listOf(
    Region("01", "Республика Адыгея"),
    Region("02", "Республика Башкортостан, Башкирия"),
    Region("102", "Республика Башкортостан, Башкирия"),
    Region("702", "Республика Башкортостан, Башкирия"),
    Region("03", "Республика Бурятия"),
    Region("103", "Республика Бурятия"),
    Region("04", "Республика Алтай"),
    Region("05", "Республика Дагестан"),
    Region("06", "Республика Ингушетия"),
    Region("07", "Кабардино-Балкарская Республика, Кабардино-Балкария"),
    Region("08", "Республика Калмыкия"),
    Region("09", "Карачаево-Черкесская Республика, Карачаево-Черкессия"),
    Region("109", "Карачаево-Черкесская Республика, Карачаево-Черкессия"),
    Region("10", "Республика Карелия"),
    Region("11", "Республика Коми"),
    Region("111", "Республика Коми"),
    Region("12", "Республика Марий Эл"),
    Region("13", "Республика Мордовия"),
    Region("113", "Республика Мордовия"),
    Region("14", "Республика Саха (Якутия)"),
    Region("15", "Республика Северная Осетия — Алания"),
    Region("16", "Республика Татарстан"),
    Region("116", "Республика Татарстан"),
    Region("716", "Республика Татарстан"),
    Region("17", "Республика Тыва"),
    Region("18", "Удмуртская Республика, Удмуртия"),
    Region("118", "Удмуртская Республика, Удмуртия"),
    Region("19", "Республика Хакасия"),
    Region("21", "Чувашская Республика, Чувашия"),
    Region("121", "Чувашская Республика, Чувашия"),
    Region("22", "Алтайский край"),
    Region("122", "Алтайский край"),
    Region("23", "Краснодарский край"),
    Region("93", "Краснодарский край"),
    Region("123", "Краснодарский край"),
    Region("193", "Краснодарский край"),
    Region("323", "Краснодарский край"),
    Region("24", "Красноярский край"),
    Region("84", "Красноярский край"),
    Region("88", "Красноярский край"),
    Region("124", "Красноярский край"),
    Region("25", "Приморский край, Приморье"),
    Region("125", "Приморский край, Приморье"),
    Region("725", "Приморский край, Приморье"),
    Region("26", "Ставропольский край"),
    Region("126", "Ставропольский край"),
    Region("27", "Хабаровский край"),
    Region("28", "Амурская область"),
    Region("29", "Архангельская область"),
    Region("30", "Астраханская область"),
    Region("130", "Астраханская область"),
    Region("31", "Белгородская область"),
    Region("32", "Брянская область"),
    Region("33", "Владимирская область"),
    Region("34", "Волгоградская область"),
    Region("134", "Волгоградская область"),
    Region("35", "Вологодская область"),
    Region("36", "Воронежская область"),
    Region("136", "Воронежская область"),
    Region("37", "Ивановская область"),
    Region("38", "Иркутская область"),
    Region("138", "Иркутская область"),
    Region("39", "Калининградская область"),
    Region("91", "Калининградская область"),
    Region("139", "Калининградская область"),
    Region("40", "Калужская область"),
    Region("41", "Камчатский край, Камчатка"),
    Region("42", "Кемеровская область"),
    Region("142", "Кемеровская область"),
    Region("43", "Кировская область"),
    Region("44", "Костромская область"),
    Region("45", "Курганская область"),
    Region("46", "Курская область"),
    Region("47", "Ленинградская область"),
    Region("147", "Ленинградская область"),
    Region("48", "Липецкая область"),
    Region("49", "Магаданская область"),
    Region("50", "Московская область"),
    Region("90", "Московская область"),
    Region("150", "Московская область"),
    Region("190", "Московская область"),
    Region("250", "Московская область"),
    Region("550", "Московская область"),
    Region("750", "Московская область"),
    Region("790", "Московская область"),
    Region("51", "Мурманская область"),
    Region("52", "Нижегородская область"),
    Region("152", "Нижегородская область"),
    Region("252", "Нижегородская область"),
    Region("53", "Новгородская область"),
    Region("54", "Новосибирская область"),
    Region("154", "Новосибирская область"),
    Region("754", "Новосибирская область"),
    Region("55", "Омская область"),
    Region("155", "Омская область"),
    Region("56", "Оренбургская область"),
    Region("156", "Оренбургская область"),
    Region("57", "Орловская область"),
    Region("58", "Пензенская область"),
    Region("158", "Пензенская область"),
    Region("59", "Пермский край"),
    Region("81", "Пермский край"),
    Region("159", "Пермский край"),
    Region("60", "Псковская область"),
    Region("61", "Ростовская область"),
    Region("161", "Ростовская область"),
    Region("761", "Ростовская область"),
    Region("62", "Рязанская область"),
    Region("63", "Самарская область"),
    Region("163", "Самарская область"),
    Region("763", "Самарская область"),
    Region("64", "Саратовская область"),
    Region("164", "Саратовская область"),
    Region("65", "Сахалинская область"),
    Region("66", "Свердловская область"),
    Region("96", "Свердловская область"),
    Region("196", "Свердловская область"),
    Region("67", "Смоленская область"),
    Region("68", "Тамбовская область"),
    Region("69", "Тверская область"),
    Region("169", "Тверская область"),
    Region("70", "Томская область"),
    Region("71", "Тульская область"),
    Region("72", "Тюменская область"),
    Region("172", "Тюменская область"),
    Region("73", "Ульяновская область"),
    Region("173", "Ульяновская область"),
    Region("74", "Челябинская область"),
    Region("174", "Челябинская область"),
    Region("774", "Челябинская область"),
    Region("75", "Забайкальский край"),
    Region("76", "Ярославская область"),
    Region("77", "Москва"),
    Region("97", "Москва"),
    Region("99", "Москва"),
    Region("177", "Москва"),
    Region("197", "Москва"),
    Region("199", "Москва"),
    Region("777", "Москва"),
    Region("797", "Москва"),
    Region("799", "Москва"),
    Region("977", "Москва"),
    Region("277", "Москва"),
    Region("299", "Москва"),
    Region("78", "Санкт-Петербург"),
    Region("98", "Санкт-Петербург"),
    Region("178", "Санкт-Петербург"),
    Region("198", "Санкт-Петербург"),
    Region("79", "Еврейская автономная область"),
    Region("80", "Донецкая Народная Республика, ДНР"),
    Region("180", "Донецкая Народная Республика, ДНР"),
    Region("81", "Луганская Народная Республика, ЛНР"),
    Region("181", "Луганская Народная Республика, ЛНР"),
    Region("82", "Республика Крым"),
    Region("83", "Ненецкий автономный округ"),
    Region("84", "Херсонская область"),
    Region("184", "Херсонская область"),
    Region("85", "Запорожская область"),
    Region("185", "Запорожская область"),
    Region("86", "Ханты-Мансийский автономный округ — Югра"),
    Region("186", "Ханты-Мансийский автономный округ — Югра"),
    Region("87", "Чукотский автономный округ"),
    Region("88", "Красноярский край"),
    Region("89", "Ямало-Ненецкий автономный округ"),
    Region("92", "Севастополь"),
    Region("192", "Севастополь"),
    Region("94", "Территории, находящиеся за пределами РФ"),
    Region("95", "Чеченская республика"),
    Region("96", "Свердловская область"),
    Region("97", "Москва"),
    Region("98", "Санкт-Петербург"),
    Region("99", "Москва")
)

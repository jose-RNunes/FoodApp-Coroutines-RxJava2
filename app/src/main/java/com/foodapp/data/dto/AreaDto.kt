package com.foodapp.data.dto

import com.foodapp.domain.model.Area

sealed class AreaDto{

    data class Response(val strArea: String){
        fun toArea() = Area(area = strArea)
    }
}
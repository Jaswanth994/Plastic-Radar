package com.example.plastic_radar


sealed class State(val name: String, val districts: List<District>) {
    object Kerala : State("KERALA", listOf(District("ERNAKULAM"), District("KOZHIKODE"), District("TRIVANDRUM"), District("THRISSUR")))
    object TamilNadu : State("TAMILNADU", listOf(District("CHENNAI"), District("COIMBATORE"), District("MADURAI")))
    object Karnataka : State("KARNATAKA", listOf(District("BANGALORE"), District("MYSORE"), District("MANGALORE")))
    object AndhraPradesh : State("ANDHRA PRADESH", listOf(District("VIJAYAWADA"), District("VISAKHAPATNAM"), District("GUNTUR")))
    object Telangana : State("TELANGANA", listOf(District("HYDERABAD"), District("WARANGAL")))
    object Maharashtra : State("MAHARASHTRA", listOf(District("MUMBAI"), District("PUNE"), District("NAGPUR")))
    object Punjab : State("PUNJAB", listOf(District("AMRITSAR"), District("LUDHIANA")))
    object Haryana : State("HARYANA", listOf(District("GURGAON"), District("FARIDABAD")))
}

data class District(val name: String)


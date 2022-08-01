package com.example.booksalonappointment.model.remote.response

data class Services(
    val Beard_styles: List<BeardStyle>,
    val Combo_Offers: List<ComboOffer>,
    val Hair_colors: List<HairColor>,
    val Haircuts: List<Haircut>,
    val Head_Massage: List<HeadMassage>,
    val Massages_Spa: List<MassagesSpa>,
    val Official_looks: List<OfficialLook>,
    val Shaves: List<Shave>,
    val facial: List<Facial>
)
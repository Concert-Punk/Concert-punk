
    function handleSearch(artist) {
    let queryURL = "https://app.ticketmaster.com/discovery/v2/events.json?&countryCode=US&sort'date,asc'&keyword=" +
    artist + "&apikey=oiPygQlAdZyxwwtbOi99vUDR8HTGkuV8"
    $.ajax({
    type: "GET",
    url: queryURL,
    async: true,
    dataType: "json",
    success: function (response) {
    if (response.hasOwnProperty('_embedded')) {
    // console.log("It has property embedded");
    console.log(response)
    let evts = response._embedded.events;
    let concertHeader;
    for (let i = 0; i < 5; i++) {
    // console.log(response._embedded.events[i]);
    let concertDiv = $("<div class='item'>");
    // let images = evts[i]_embedded.events[0].images[0]
    let tourName = evts[i].name;
    let tourDate = evts[i].dates.start.localDate;
    let tourTime = evts[i].dates.start.localTime;
    let momentTourTime = moment('2015-12-11 '+ tourTime).format('hA')
    let venue = evts[i]._embedded.venues[0].name;
    let showCity = evts[i]._embedded.venues[0].city.name;
    let showState = evts[i]._embedded.venues[0].state.stateCode;
    let buyLink = evts[i].url;
    // console.log(buyLink)
    let results = `  <div class="flip-card-container" style="--hue: 220 ">
  <div class="flip-card">

    <div class="card-front" id="card-front">
      <figure>
        <div class="img-bg"></div>
        <img src="https://source.unsplash.com/random" alt="Brohm Lake">
        <h4 class="artistName">${tourName}</h4>
      </figure>

      <ul id="details">
        <li id  ="detailItems">${tourDate}</li>
        <li>${venue}</li>
        <li>${momentTourTime}</li>
        <li>${showState}</li>
        <li>${showCity}</li>
      </ul>
    </div>

    <div class="card-back">
      <figure>
        <div class="img-bg"></div>
<!--        <img src="/images/sky.jpeg" >-->
      </figure>
      <button class="details"  type="submit" data-artistName="${tourName}" id="saveBtn">More Info</button>
      <div class="design-container">
        <span class="design design--1"></span>
        <span class="design design--2"></span>
        <span class="design design--3"></span>
        <span class="design design--4"></span>
        <span class="design design--5"></span>
        <span class="design design--6"></span>
        <span class="design design--7"></span>
        <span class="design design--8"></span>
      </div>
    </div>
  </div>
</div>
<div class="buyTicketBtn">
<a class="buyTicketBtnHref" href="${buyLink}"><i class="fas fa-ticket-alt" style="display-flex; flex-wrap:wrap"></i> TICKETS</a>
</div>
`
    concertDiv.prepend(results);

    $("#concertSchedule").append(concertDiv);
}
} else {
    $("#concertSchedule").html("<em>" + "There currently isn't any event information for " + artist + ".</em>");
}
},
    error: function (xhr, status, err) {
    // This time, we do not end up here!
}
});
}

    $(document).on('click', '#add-artist', function (event) {
    event.preventDefault();
    let artist = $("#search-input").val().trim();
    // console.log(artist);
    handleSearch(artist);
})

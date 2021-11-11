function handleSearch(artist) {
    console.log(artist);
    var queryURL = "https://app.ticketmaster.com/discovery/v2/events.json?&countryCode=US&sort'date,asc'&keyword=" +
        artist + "&apikey= " + ticketMasterKey
    console.log(queryURL)
    $.ajax({
        type: "GET",
        url: queryURL,
        async: true,
        dataType: "json",
        success: function (response) {
            if (response.hasOwnProperty('_embedded')) {
                console.log("It has property embedded");
                var evts = response._embedded.events;
                $("#concertSchedule").empty();
                for (var i = 0; i < 5; i++) {
                    console.log(response._embedded.events[i]);
                    var concertDiv = $("<div class='item'>");
                    var tourName = evts[i].name;
                    var tourDate = evts[i].dates.start.localDate;
                    var tourTime = evts[i].dates.start.localTime;
                    var tourTimeC = moment(tourTime, "HH:mm").format('hh:mm a');
                    var venue = evts[i]._embedded.venues[0].name;
                    var showCity = evts[i]._embedded.venues[0].city.name;
                    var showState = evts[i]._embedded.venues[0].state.stateCode;
                    var buyLink = evts[i].url;
                    var results = $("<div class='searchResult'>").html("<strong>Artist/Tour Name: " + tourName + "</strong><br>" +
                        "Concert Date: " + tourDate + " Time: " + tourTimeC + "<br>" +
                        "Venue: " + venue + " - " + showCity + ", " + showState + "<br>" +
                        "Ticket Link: " + "<a href='" + buyLink + "' target=_'blank'>Click Here for Tickets</a>" + "<br>");
                    concertDiv.prepend(results);
                    $("#concertSchedule").append(concertDiv);
                }
            } else {
                $("#concertSchedule").html("<em>" + "There currently isnt any event information for " + artist + ".</em>");
            }
        },
        error: function (xhr, status, err) {
            // This time, we do not end up here!
        }
    });
}

$(document).on('click', '#add-artist', function (event) {
    event.preventDefault();
    var artist = $("#search-input").val().trim();
    console.log(artist);
    handleSearch(artist);


});
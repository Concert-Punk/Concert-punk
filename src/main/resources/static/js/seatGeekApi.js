
    $.ajax({
    type: "GET",
    url: "https://api.seatgeek.com/2/events?client_id=MjM5OTczOTd8MTYzNDY3MzY2Ni42ODE5ODE&geoip=true",
    async: true,
    dataType: "json",
    success: function (data) {
    let cardMaker = ''
    let concerts = data.events.filter(element => element.type === 'concert');
    console.log(concerts)
    for (const concerts of data.events.filter(element => element.type === 'concert')) {
    let image = concerts.performers[0].image
    let ticketPurchaseUrl = concerts.url
    let artistIndex = concerts.title
    console.log(artistIndex);
    cardMaker += "<div class='d-inline-block'>"
    cardMaker += ` <div class='card' style='width: 18rem;'>`
    cardMaker += `<a id="url" href="${ticketPurchaseUrl}"><img class="card-img-top" src='${image}' alt=' img'></a>`
    cardMaker += `<div class='card-body'><h2> <strong>${concerts.title}</strong> </h2> </div>`;
    cardMaker += `<div class='card-text'><h3> @${concerts.venue.name}</h3> </div>`;
    //   cardMaker +=`<div class="card-text"><p>${concerts.performers.genres.name}</p>`
    cardMaker += `<div class='card-text'><p>${concerts.datetime_local}</p></div>`;
    if (userIsLoggedIn) {
    cardMaker += `<div><button class="saveEvent" data-id="${concerts.id}" type="submit">Save</button></div>
    </div><br>`
}






    cardMaker += "</div>";
    console.log(userIsLoggedIn);
}
    $(".probablywontwork").html(cardMaker)
    addListener();
},
    error: function (xhr, status, err) {
}
});



    function addListener() {
    $('.saveEvent').click(function (e) {

        e.preventDefault();
        var events_id = ($(this).data("id"));
        console.log(events_id)

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        console.log(token);
        console.log(header);
        console.log(events_id);
        var data = events_id;
        //Remove all errors
        $.ajax({
            type: "GET",
            url: `https://api.seatgeek.com/2/events/${events_id}?client_id=MjM5OTczOTd8MTYzNDY3MzY2Ni42ODE5ODE&geoip=true`,
            async: true,
            dataType: "json",
            success: function (res) {
                console.log(res)

                const savedEvent = {
                    api_id: res.id,
                    title: res.title,
                    location: res.venue.name,
                    startTime: res.datetime_local,
                }
                saveEvent(savedEvent);
            }
        })


        function saveEvent(eventFromAPI) {
            $.post({
                url: '/saveEvent',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(eventFromAPI),
                beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader('X-CSRF-Token', token,)
                },
                dataType: "json",
                success: function (html) {
                    console.log(html);
                }
            });
        }
    })
}




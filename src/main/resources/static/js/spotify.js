$(document).ready(function () {
    let clickedKeyword = "";

    function createAccessToken(callback) {
    let queryURL = "https://accounts.spotify.com/api/token";
    $.ajax({
    url: queryURL,
    method: "POST",
    data: {
    "grant_type": "client_credentials"
},
    headers: {
    "Authorization": "Basic " + btoa("e81f9ea2e64e43cb834bd3488f779729" + ":" + "3850ad8ffc3c46babb2c6526acb943da")
}
}).done(function (response) {
    // console.log(response.access_token);
    sessionStorage.setItem("accessToken", response.access_token);
    callback(response.access_token);
});

}


    function getAccessToken(callback) {
    let accessToken = sessionStorage.getItem("accessToken");
    if (accessToken !== null) {
    callback(accessToken);
} else {
    createAccessToken(callback);
}
}

    function searchWithToken(accessToken) {
    let artist = $("#search-input").val().trim();
    spotifySearch(accessToken, artist);
    $("#displayArtist").text(titleCase(artist));
}

    function reSearch(accessToken) {
    spotifySearch(accessToken, encodeURI(clickedKeyword));
}

    function spotifySearch(accessToken, artist) {
    // console.log(accessToken);
    let queryURL = "https://api.spotify.com/v1/search?q=" + artist + "&type=artist&market=US&limit=10";
    // console.log(queryURL);
    // console.log(artist);
    $.ajax({
    url: queryURL,
    method: "GET",
    headers: {
    "Authorization": "Bearer " + accessToken,
}
}).done(function (response) {
    var infourl = 'http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist='+ artist +'&api_key=ac83636465b06e3626587c01f7d85bba&format=json'




    let results = response;
    console.log(results);
    let artistID = results.artists.items[0].id;
    let spotifyArtistImage = response.artists.items[0].images[0].url
    let artistGenre = response.artists.items[0].genres[0]
    axios.get(infourl).then(response => {
    let artistBio = response.data.artist.bio.content
    console.log(artistGenre)
    console.log(spotifyArtistImage)
    let moreArtistInfoContainer = `<div class="media">
                <img width="300px" height="300px" src="${spotifyArtistImage}" class="mr-3" alt="...">
                    <div class="media-body">
                        <h3><em>${artist}</em></h3>
                        <p>${artistBio}</p>
                    </div>
            </div>
                <hr>`
//
//             `<img  style="width:300px;height:300px " src="${spotifyArtistImage}">
// <p style="color:black">${artistGenre}</p>
// <p style="color:black">${artistBio}</p>

    console.log(artistBio)
    $('.artistInfo').append(moreArtistInfoContainer)
    // console.log(results.artists.items[0]);
    spotifyRelatedArtist(accessToken, artistID);
})
});

}

    // function spotifyTopTracks(accessToken, artistID) {
    //     $("#tracksContainer")
    //     let queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/top-tracks?country=US";
    //     $.ajax({
    //         url: queryURL,
    //         method: "GET",
    //         headers: {
    //             "Authorization": "Bearer " + accessToken,
    //         }
    //     }).done(function (response) {
    //         let results = response;
    //         console.log(results);
    //         for (let i = 0; i < Math.min(results.tracks.length, 1); i++) {
    //
    //             let trackName = results.tracks[i].name;
    //             let audioPreview = results.tracks[i].preview_url;
    //
    //             let albumDiv = $("<a>");
    //             albumDiv.addClass("music-item");
    //             albumDiv.text(trackName);
    //
    //             let image = $("<img>");
    //             image.attr("src", results.tracks[i].album.images[1].url);
    //             console.log(image);
    //
    //
    //
    //             document.getElementById('musicSample').innerHTML += `
    //                  <audio controls id="musicSample"> <source src='${audioPreview}'/></audio> `;
    //
    //             console.log(audioPreview)
    //
    //
    //             albumDiv.append(image);
    //             $("#tracksContainer").append("albumDiv");
    //         }
    //     });
    // }

    function spotifyRelatedArtist(accessToken, artistID) {


    let queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/related-artists";
    $.ajax({
    url: queryURL,
    method: "GET",
    headers: {
    "Authorization": "Bearer " + accessToken,
}
}).done(function (response) {
    let results = response;
    console.log(response)
    for (let j = 0; j < 5; j++) {
    let relatedArtist = results.artists[j].name;
    // console.log(relatedArtist)
    let relatedArtistImagePath = response.artists[j].images[0].url
    let spotifyUrl= response.artists[j].external_urls.spotify
    let spotifyRelatedArtistGenre =  response.artists[j].genres
    let RelatedArtistList = `<div class="media">
                    <img width="200px" height="200px" src="${relatedArtistImagePath}" class="mr-3" alt="...">
                        <div class="media-body">
                            <h3><em>${relatedArtist}</em></h3>
            <p>${spotifyRelatedArtistGenre}</p>
                    <a href="${spotifyUrl}"><img src="/images/spotifyIcon.png" alt="" style="width:30px;height:30px;"></a>
                        </div>
                </div>    <div id="tracksContainer"></div> <hr>`


    $("#relatedArtistContainer").append(RelatedArtistList);
}

});

}




    function titleCase(str) {
    str = str.toLowerCase().split(' ');
    for (let i = 0; i < str.length; i++) {
    str[i] = str[i].split('');
    str[i][0] = str[i][0].toUpperCase();
    str[i] = str[i].join('');
}
    return str.join(' ');
}


    $("#add-artist").on("click", function (event) {
    event.preventDefault();
    getAccessToken(searchWithToken);
});
    $(document).on("click", ".search-keyword", function (event) {
    event.preventDefault();
    clickedKeyword = $(this).text();
    getAccessToken(reSearch);
    $("#displayArtist").text($(this).text());

});


});

    $(document).on("click", '.details', function () {
        $('#detailsModel').modal('show')
    });



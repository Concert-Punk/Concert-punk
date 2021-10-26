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
                "Authorization": "Basic " + btoa(spotifyClientId + ":" + spotifyClientSecret)
            }
        }).done(function (response) {
            console.log(response.access_token);
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
        console.log(accessToken);
        let queryURL = "https://api.spotify.com/v1/search?q=" + artist + "&type=artist&market=US&limit=10";
        console.log(queryURL);
        console.log(artist);
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function (response) {
            let results = response;
            console.log(results);
            let artistID = results.artists.items[0].id;
            // console.log(results.artists.items[0]);
            spotifyTopTracks(accessToken, artistID);
            spotifyRelatedArtist(accessToken, artistID);
        });

    }


    function spotifyTopTracks(accessToken, artistID) {
        $("#tracksContainer")
        let queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/top-tracks?country=US";
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function (response) {
            let results = response;
            console.log(results);
            for (let i = 0; i < Math.min(results.tracks.length, 5); i++) {

                let trackName = results.tracks[i].name;
                let audioPreview = results.tracks[i].preview_url;

                let albumDiv = $("<a>");
                albumDiv.addClass("music-item");
                albumDiv.text(trackName);

                let image = $("<img>");
                image.attr("src", results.tracks[i].album.images[1].url);
                console.log(image);



                document.getElementById('musicSample').innerHTML += `
                     <audio controls> <source src='${audioPreview}'/></audio> `;

                       console.log(audioPreview)


                albumDiv.append(image);
                $("#tracksContainer").append(albumDiv);
            }
        });
    }


    function spotifyRelatedArtist(accessToken, artistID) {
        $("#relatedArtistContainer").empty();
        console.log(artistID);
        let queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/related-artists";
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function (response) {
            let results = response;
            console.log(results);

            for (let j = 0; j < 5; j++) {
                let relatedArtist = results.artists[j].name;

                let displayRelatedArtist = $('<li class="search-keyword">' + relatedArtist + '</li>').css("margin", "5px");

                $("#relatedArtistContainer").append(displayRelatedArtist);
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

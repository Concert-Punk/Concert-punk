$(document).ready(function() {
    var clickedKeyword = "";


    function createAccessToken(callback) {
        var queryURL = "https://accounts.spotify.com/api/token";
        $.ajax({
            url: queryURL,
            method: "POST",
            data: {
                "grant_type": "client_credentials"
            },
            headers: {
                "Authorization": "Basic " + btoa(clientid + ":" + clientSecret)
            }
        }).done(function(response) {
            console.log(response.access_token);
            sessionStorage.setItem("accessToken", response.access_token);
            callback(response.access_token);
        });

    }


    function getAccessToken(callback) {
        var accessToken = sessionStorage.getItem("accessToken");
        if (accessToken !== null) {
            callback(accessToken);
        }
        else {
            createAccessToken(callback);
        }
    }

    function searchWithToken(accessToken) {
        var artist = $("#search-input").val().trim();
        spotifySearch(accessToken, artist);
        $("#displayArtist").text(titleCase(artist));
    }

    function reSearch(accessToken) {
        spotifySearch(accessToken, encodeURI(clickedKeyword));
    }

    function spotifySearch(accessToken, artist) {
        console.log(accessToken);
        var queryURL = "https://api.spotify.com/v1/search?q=" + artist + "&type=artist&market=US&limit=10";
        console.log(queryURL);
        console.log(artist);
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function(response) {
            var results = response;
            console.log(results);
            var artistID = results.artists.items[0].id;
            // console.log(results.artists.items[0]);
            spotifyTopTracks(accessToken, artistID);
            spotifyRelatedArtist(accessToken, artistID);
        });

    }


    function spotifyTopTracks(accessToken, artistID) {
        $("#tracksContainer")
        var queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/top-tracks?country=US";
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function(response) {
            var results = response;
            console.log(results);
            for (var i = 0; i < Math.min(results.tracks.length, 5); i++) {

                var trackName = results.tracks[i].name;

                var albumDiv = $("<a>");
                albumDiv.addClass("music-item");
                albumDiv.text(trackName);

                var image = $("<img>");
                image.attr("src", results.tracks[i].album.images[1].url);
                console.log(image);

                albumDiv.append(image);
                $("#tracksContainer").append(albumDiv);
            }
        });
    }



    function spotifyRelatedArtist(accessToken, artistID) {
        $("#relatedArtistContainer").empty();
        console.log(artistID);
        var queryURL = "https://api.spotify.com/v1/artists/" + artistID + "/related-artists";
        $.ajax({
            url: queryURL,
            method: "GET",
            headers: {
                "Authorization": "Bearer " + accessToken,
            }
        }).done(function(response) {
            var results = response;
            console.log(results);

            for (var j = 0; j < 5; j++) {
                var relatedArtist = results.artists[j].name;

                var displayRelatedArtist = $('<li class="search-keyword">' + relatedArtist + '</li>').css("margin", "5px");

                $("#relatedArtistContainer").append(displayRelatedArtist);
            }

        });

    }

    function titleCase(str) {
        str = str.toLowerCase().split(' ');
        for (var i = 0; i < str.length; i++) {
            str[i] = str[i].split('');
            str[i][0] = str[i][0].toUpperCase();
            str[i] = str[i].join('');
        }
        return str.join(' ');
    }


    $("#add-artist").on("click", function(event) {
        event.preventDefault();
        getAccessToken(searchWithToken);
    });
    $(document).on("click", ".search-keyword", function(event) {
        event.preventDefault();
        clickedKeyword = $(this).text();
        getAccessToken(reSearch);
        $("#displayArtist").text($(this).text());

    });


});

# xkcdViewer
Two Screen Kotlin Android Application utilizing the public api for [xkcd comics](https://xkcd.com/) available [here](https://xkcd.com/info.0.json) to create a simple comic strip viewer. 

## Implementation
The limitations of the the public API means I cannot query a range of entries or chain requests like what may be possible with more sophisticated API's. The only lookups available are comicById and comicByLatest.

Screen 1: SingleComicViewFragment to display a single comic at a time, fresco for image loading. This view has a navigation bar to allow users to move in a limited fashion through the comic history, lite logic to ensure the id never goes out of bounds

Screen 2: SingleComicInfoFragment to display a single comics complete metadata without an image, including title, alt text, and transcript, release Date build from year/mon/day

### Dependencies
* GSON:       Serialize/Deserialize
* Retrofit:   Type-Safe Http Client
* Coroutune:  Kotlin Threading
* Freso:      Image Loading and Placeholders

#### Disclaimers and Licenses
I did not write or contribute in any meaningful way to the production or distribution any xkcd comic(s) by Randall Munroe, they are licensed under the [Creative Commons Attribution-NonCommerical 2.5 License](https://xkcd.com/license.html) for noncommerical reuse and copy. 

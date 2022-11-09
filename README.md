# xkcdViewer
Two Screen Kotlin Android Application utilizing the public api for [xkcd comics](https://xkcd.com/) available [here](https://xkcd.com/info.0.json) to create a simple comic strip viewer. 

## Implementation
Screen 1: RecyclerView to list comics in order by their meta data, displaying minimum information

The limitations of the the public API mean I cannot query a range of entries or chain requests like what may be possible with more sophisticated API's. The only lookups available are comicById and comicByLatest. 

My idea for a fluid user experience is to create some of that functionality myself, comicsByRange and comicsByTitle (fuzzy matching). To do this I want to mask inside the initial load of the application a group of about ~3k get requests and subsequent writes into a relational database. These requests will contain the comic's entire history of metadata(everything but the images).
Whether or not I will find success with this project, requires that I can, without locking up or creating a negative user experience:
* Make these requests quickly
* Make these writes quickly

Screen 2: ComicView to display a single comic, along with its image to be loaded on request

Easy too peazy. I want the alternate text to hidden until found just like the original viewing experience. May a hold click will reveal it or a dedicated button or something. 

### Dependencies
* Freso:      Image Loading and Placeholders
* Retrofit:   Httpcalls
* Room:       Caching non trivial amounts of comic metadata

#### Disclaimers and Licenses
I did not write or contribute in any meaningful way to the production or distribution any xkcd comic(s), they are licensed under the [Creative Commons Attribution-NonCommerical 2.5 License](https://xkcd.com/license.html) for noncommerical reuse and copy. 

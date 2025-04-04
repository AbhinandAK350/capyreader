package com.jocmp.rssparser.internal.atom

internal sealed class AtomKeyword(val value: String) {
    operator fun invoke() = value

    data object Atom : AtomKeyword("feed")
    data object Title : AtomKeyword("title")
    data object Icon : AtomKeyword("icon")
    data object Link : AtomKeyword("link") {
        data object Href : AtomKeyword("href")
        data object Rel : AtomKeyword("rel")
        data object Edit : AtomKeyword("edit")
        data object Self : AtomKeyword("self")
        data object Replies : AtomKeyword("replies")
        data object Enclosure : AtomKeyword("enclosure")
    }

    data object Subtitle : AtomKeyword("subtitle")
    data object Updated : AtomKeyword("updated")
    data object Entry {
        data object Tag : AtomKeyword("entry")
        data object Guid : AtomKeyword("id")
        data object Content : AtomKeyword("content")
        data object Published : AtomKeyword("published")
        data object Category : AtomKeyword("category")
        data object Term : AtomKeyword("term")
        data object Description : AtomKeyword("summary")
        data object Author {
            data object Author : AtomKeyword("author")
            data object Name : AtomKeyword("name")
        }

        data object Email : AtomKeyword("email")

        data object YouTubeVideoID: AtomKeyword("yt:videoId")

        data object Media {
            data object Group : AtomKeyword("media:group")
            data object Title : AtomKeyword("media:title")
            data object Content : AtomKeyword("media:content")
            data object Thumbnail : AtomKeyword("media:thumbnail")
            data object Description : AtomKeyword("media:description")
        }
    }
}

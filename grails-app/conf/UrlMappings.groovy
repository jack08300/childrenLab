class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/getAvatar"(controller: "avatar", action: "getUserAvatar")
        "/"(controller: 'home')
        "500"(controller: "error", action: "internalServerError")
	}
}

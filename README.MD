# Greeter
The application exposes following endpoints at port **9090** 
- `/health-check`
- `/hello` 
- `/joke`

---
## Usage

### Health check endoint

Send across a HTTP GET request to path `/health-check` for response with content `Okay`

### Greet endpoint 

Send across a HTTP GET request to path `/greet/<name>` for a JSON response 
`{"message":"Hello, <name>"}`

### Joke endpoint

Send across a HTTP GET request with path `/joke` for random joke in the format 
`{"joke":"<random-joke>"}`


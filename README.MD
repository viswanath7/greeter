# Greeter
The application exposes two endpoints 
- `/hello` 
- `/joke` 
at port **9090**
---
## Usage

### Hello endpoint 

Send across a HTTP GET request to path `/hello/<name>` for a JSON response 
`{"message":"Hello, <name>"}`

### Joke endpoint

Send across a HTTP GET request with path `/joke` for random joke in the format 
`{"joke":"<random-joke>"}`


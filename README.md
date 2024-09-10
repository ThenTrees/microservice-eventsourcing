thentrees

### command -> write site, query -> read site
pull and run axonserver
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver

projection in query -> listen query, then query into db and response data. Projection usualy used build architectures only read to optimize
queries complicated.
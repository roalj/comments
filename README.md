docker image build . -t rkosir123/comments
docker run -itd --network=rso --network-alias=comments -p 8081:8081 rkosir123/comments
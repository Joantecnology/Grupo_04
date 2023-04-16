import React, { useState, useEffect } from "react";
import axios from "axios";
import './ImageGallery.scss'

function ImageGallery() {
  const [images, setImages] = useState([]);

  useEffect(() => {
    async function fetchImages() {
      const response = await axios.get("/images.json");
      setImages(response.data.images);
    }

    fetchImages();
  }, []);

  return (
    <div className="img-control">
      {images.map((image) => (
        <div key={image.src}>
          <img src={image.src} alt={image.alt} />
          <h2>{image.title}</h2>
          <p>{image.description}</p>
        </div>
      ))}
    </div>
  );
}

export default ImageGallery;

package org.firstinspires.ftc.teamcode.Core.Tfod;


import java.util.List;

import org.firstinspires.ftc.vision.VisionProcessor;

public abstract class EctoTfodProcessor implements VisionProcessor
{
    public static EctoTfodProcessor easyCreateWithDefaults() {
        return new Builder().build();
    }

    public static class Builder {
        private final TfodParameters.Builder builder = new TfodParameters.Builder();



        /**
         * Set the name of the asset where the model is found.
         */
        public Builder setModelAssetName(String assetName) {
            builder.setModelAssetName(assetName);
            return this;
        }

        /**
         * Set the name of the file where the model is found.
         */
        public Builder setModelFileName(String fileName) {
            builder.setModelFileName(fileName);
            return this;
        }

        /**
         * Set the full ordered list of labels the model is trained to recognize.
         */
        public Builder setModelLabels(List<String> labels) {
            builder.setModelLabels(labels);
            return this;
        }
        public Builder setModelLabels(String[] labels) {
            builder.setModelLabels(labels);
            return this;
        }

        /**
         * Set whether the model is a TensorFlow2 model.
         */
        public Builder setIsModelTensorFlow2(boolean isModelTensorFlow2) {
            builder.setIsModelTensorFlow2(isModelTensorFlow2);
            return this;
        }

        /**
         * Set whether the model is quantized.
         */
        public Builder setIsModelQuantized(boolean isModelQuantized) {
            builder.setIsModelQuantized(isModelQuantized);
            return this;
        }

        /**
         * Set the size, in pixels, of images input to the network.
         */
        public Builder setModelInputSize(int inputSize) {
            builder.setModelInputSize(inputSize);
            return this;
        }

        /**
         * Set the aspect ratio for the images used when the model was created.
         */
        public Builder setModelAspectRatio(double modelAspectRatio) {
            builder.setModelAspectRatio(modelAspectRatio);
            return this;
        }

        /**
         * Set the number of executor threads to use. Each executor corresponds to one TensorFlow
         * Object Detector.
         */
        public Builder setNumExecutorThreads(int numExecutorThreads) {
            builder.setNumExecutorThreads(numExecutorThreads);
            return this;
        }

        /**
         * Set the number of threads to allow each individual TensorFlow object detector to use.
         */
        public Builder setNumDetectorThreads(int numDetectorThreads) {
            builder.setNumDetectorThreads(numDetectorThreads);
            return this;
        }

        /**
         * Set the maximum number of recognitions the network will return.
         */
        public Builder setMaxNumRecognitions(int maxNumRecognitions) {
            builder.setMaxNumRecognitions(maxNumRecognitions);
            return this;
        }

        /**
         * Set whether to use the tracker.
         */
        public Builder setUseObjectTracker(boolean useObjectTracker) {
            builder.setUseObjectTracker(useObjectTracker);
            return this;
        }

        /**
         * Set the maximum percentage of a box that can be overlapped by another box at recognition time.
         */
        public Builder setTrackerMaxOverlap(float trackerMaxOverlap) {
            builder.setTrackerMaxOverlap(trackerMaxOverlap);
            return this;
        }

        /**
         * Set the minimum size of an object that the tracker will track.
         */
        public Builder setTrackerMinSize(float trackerMinSize) {
            builder.setTrackerMinSize(trackerMinSize);
            return this;
        }

        /**
         * Allow replacement of the tracked box with new results if correlation has dropped below
         * trackerMarginalCorrelation.
         */
        public Builder setTrackerMarginalCorrelation(float trackerMarginalCorrelation) {
            builder.setTrackerMarginalCorrelation(trackerMarginalCorrelation);
            return this;
        }

        /**
         * Consider an object to be lost if correlation falls below trackerMinCorrelation.
         */
        public Builder setTrackerMinCorrelation(float trackerMinCorrelation) {
            builder.setTrackerMinCorrelation(trackerMinCorrelation);
            return this;
        }

        /**
         * Returns a TfodProcessor object.
         */
        public EctoTfodProcessor build() {
            return new EctoTfodProcessorImpl(builder.build());
        }
    }

    /**
     * Set the minimum confidence at which to keep recognitions.
     */
    public abstract void setMinResultConfidence(float minResultConfidence);

    /**
     * Sets the number of pixels to obscure on the left, top, right, and bottom edges of each image
     * passed to the TensorFlow object detector. The size of the images are not changed, but the
     * pixels in the margins are colored black.
     */
    public abstract void setClippingMargins(int left, int top, int right, int bottom);

    /**
     * Indicates that only the zoomed center area of each image will be passed to the TensorFlow
     * object detector. For no zooming, set magnification to 1.0.
     */
    public abstract void setZoom(double magnification);

    /**
     * Gets a list containing the latest recognitions, which may be stale.
     */
    public abstract List<Recognition> getRecognitions();

    /**
     * Gets a list containing recognitions that were detected since the last call to this method,
     * or null if no new recognitions are available. This is useful to avoid re-processing the same
     * recognitions multiple times.
     * @return a list containing fresh recognitions, or null.
     */
    public abstract List<Recognition> getFreshRecognitions();

    /**
     * Perform whatever cleanup is necessary to release all acquired resources.
     */
    public abstract void shutdown();
}

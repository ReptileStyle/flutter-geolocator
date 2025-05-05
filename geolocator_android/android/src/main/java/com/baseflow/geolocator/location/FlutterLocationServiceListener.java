package com.baseflow.geolocator.location;

import com.baseflow.geolocator.errors.ErrorCodes;

import io.flutter.plugin.common.MethodChannel;

public class FlutterLocationServiceListener implements LocationServiceListener {
  private MethodChannel.Result result;

  public FlutterLocationServiceListener(MethodChannel.Result result) {
    this.result = result;
  }

  @Override
  public void onLocationServiceResult(boolean isEnabled) {
    result.success(isEnabled);
  }

  @Override
  public void onLocationServiceError(ErrorCodes errorCode) {
      /// maybe called multiple times, which leads to crash, throws IllegalStateException
      try {
          result.error(errorCode.toString(), errorCode.toDescription(), null);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }
}

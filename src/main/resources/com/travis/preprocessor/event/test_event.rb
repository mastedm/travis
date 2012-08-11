#!/usr/bin/env ruby

require 'abstract_event'

class TestEvent < AbstractEvent
  @regexp = /^test \d+$/  
end
